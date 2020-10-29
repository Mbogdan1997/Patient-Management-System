package com.example.rpc.ui.interval;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.rpc.rmi.HelloWorldRMI;
import com.example.rpc.service.IntervalIntakeConverter;
import com.example.rpc.session.CurentSession;
import com.example.rpc.ui.login.LoginController;
import com.utcn.hospital.SpringConfiguration;
import com.utcn.hospital.dto.IdDTO;
import com.utcn.hospital.dto.IntakeIntervalDTO;
import com.utcn.hospital.dto.RMINotification;

@Component
public class IntervalIntakeViewTableController implements ActionListener {

	private IntervalIntakeViewTable intervalIntakeViewTable;
	private HelloWorldRMI helloWorldRMI;
	private IntervalIntakeConverter intervalIntakeConverter;
	private TimerTask repeatedTask;
	private Timer timer;

	@Autowired
	public IntervalIntakeViewTableController(IntervalIntakeConverter intervalIntakeConverter,
			IntervalIntakeViewTable intervalIntakeViewTable, HelloWorldRMI helloWorldRMI) {

		this.intervalIntakeViewTable = intervalIntakeViewTable;
		this.helloWorldRMI = helloWorldRMI;
		this.intervalIntakeConverter = intervalIntakeConverter;
		MouseListenerUserInterface mouseListenerUserInterface = new MouseListenerUserInterface();
		this.intervalIntakeViewTable.getTable().addMouseListener(mouseListenerUserInterface);
		UserVieWindowController userVieWindowController = new UserVieWindowController();
		this.intervalIntakeViewTable.getFrame().addWindowListener(userVieWindowController);
		this.intervalIntakeViewTable.addActionListener(this);

	}

	public void setViewVisible(boolean visible) {
		intervalIntakeViewTable.setFrameVisible(visible);
	}

	public void loadData() {
		IdDTO userDto = new IdDTO(CurentSession.getCurentUser().getId());
		List<IntakeIntervalDTO> intakeIntervalDTOs = helloWorldRMI.viewPacientDayDrug(new Date(), userDto);
//		intervalIntakeViewTable.refreshTable(intervalIntakeConverter.getTableData(intakeIntervalDTOs), intervalIntakeConverter.getColumnName());
//		if(!CurentSession.getLogInForFirstTime()) {
//			CurentSession.setLogInForFirstTime(true);
//			filterList(intakeIntervalDTOs);
//			
//		}
		CurentSession.setIntakeIntervalDTOs(intakeIntervalDTOs);
		filterList();
	}

	private void filterList() {

		List<IntakeIntervalDTO> tableList = CurentSession.getIntakeIntervalDTOs().stream()
				.filter(x -> x.getStartHour() <= CurentSession.getCurentHour()
						&& x.getFinishHour() > CurentSession.getCurentHour())
				.collect(Collectors.toList());

		List<IntakeIntervalDTO> outOfList = CurentSession.getIntakeIntervalDTOs().stream()
				.filter(x -> x.getStartHour() > CurentSession.getCurentHour() || x.getFinishHour() <CurentSession.getCurentHour()).collect(Collectors.toList());

		for (IntakeIntervalDTO intakeIntervalDTO : outOfList) {

			RMINotification notification = new RMINotification();
			notification.setDrugName(intakeIntervalDTO.getDrugDTO().getName());
			notification.setPatientName(intakeIntervalDTO.getMedicationPlanDTO().getPatientDTO().getName());
			notification.setStartHour(intakeIntervalDTO.getStartHour());
			notification.setFinishHour(intakeIntervalDTO.getFinishHour());
			notification.setTakeIt(false);
			CurentSession.getIntakeIntervalDTOs().remove(intakeIntervalDTO);
			helloWorldRMI.showNotification(notification);
		}

		setViewVisible(true);
		intervalIntakeViewTable.refreshTable(intervalIntakeConverter.getTableData(tableList),
				intervalIntakeConverter.getColumnName());

	}

	public void makeTimer() {
		repeatedTask = new TimerTask() {
			public void run() {
				Date nowDate = new Date();
				intervalIntakeViewTable.setHours(nowDate.getHours());
				intervalIntakeViewTable.setMinutes(nowDate.getMinutes());
				intervalIntakeViewTable.setSeconds(nowDate.getSeconds());
				if (nowDate.getHours() != CurentSession.getCurentHour()) {
					CurentSession.setCurentHour(nowDate.getHours());
					filterList();
				}
				if (nowDate.getHours() == 0 && nowDate.getMinutes() == 0 && nowDate.getSeconds() == 0) {
					loadData();
				}
			}
		};
		timer = new Timer("Timer");

		long delay = 0L;
		long period = 1000L;
		timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}

	private class MouseListenerUserInterface extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == intervalIntakeViewTable.getTable()) {
				int x = intervalIntakeViewTable.getTable().rowAtPoint(e.getPoint());
				;
				String medicationPlanId = String.valueOf(intervalIntakeViewTable.getTable().getValueAt(x, 0));
				String drugId = String.valueOf(intervalIntakeViewTable.getTable().getValueAt(x, 1));
				intervalIntakeViewTable.setId(medicationPlanId);
				intervalIntakeViewTable.setItemId(drugId);

			}
		}
	}

	private class UserVieWindowController extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {

			if (e.getSource() == intervalIntakeViewTable.getFrame()) {
				System.out.println("sdafsasdaf");
				intervalIntakeViewTable.setFrameVisible(false);
				LoginController loginController = (LoginController) SpringConfiguration.contextProvider()
						.getApplicationContext().getBean("loginController");
				loginController.setFrameVisible(true);
				timer.cancel();
				repeatedTask.cancel();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		int medicationPlanId = Integer.parseInt(intervalIntakeViewTable.getId());
		int drugId = Integer.parseInt(intervalIntakeViewTable.getItemId());
		if (!CurentSession.getIntakeIntervalDTOs().isEmpty()) {
			for (int i = 0; i < CurentSession.getIntakeIntervalDTOs().size(); i++) {
				IntakeIntervalDTO intakeIntervalDTO = CurentSession.getIntakeIntervalDTOs().get(i);
				if (intakeIntervalDTO.getMedicationPlanDTO().getId() == medicationPlanId
						&& intakeIntervalDTO.getDrugDTO().getId() == drugId) {
					
					RMINotification notification=new RMINotification();
					notification.setDrugName(intakeIntervalDTO.getDrugDTO().getName());
					notification.setPatientName(intakeIntervalDTO.getMedicationPlanDTO().getPatientDTO().getName());
					notification.setStartHour(intakeIntervalDTO.getStartHour());
					notification.setFinishHour(intakeIntervalDTO.getFinishHour());
					notification.setTakeIt(true);
					CurentSession.getIntakeIntervalDTOs().remove(i);
					helloWorldRMI.showNotification(notification);
					filterList();

				}
			}
		}

	}

}
