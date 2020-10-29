package com.utcn.hospital.rabbitmq;

import org.joda.time.DateTime;
import org.joda.time.Period;

//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.utcn.hospital.dto.Message;
import com.utcn.hospital.dto.Notification;
import com.utcn.hospital.entity.Activity;
import com.utcn.hospital.mapper.ActivityMapper;
import com.utcn.hospital.repository.ActivityRepository;

@Component
public class Receiver {

	// private CountDownLatch latch = new CountDownLatch(1);
	
	private ActivityMapper activityMapper;
	private ActivityRepository activityRepository;
	private SimpMessagingTemplate template;
	
	public Receiver() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	 public Receiver(ActivityMapper activityMapper,ActivityRepository activityRepository,SimpMessagingTemplate template) {
		// TODO Auto-generated constructor stub
		 this.activityMapper=activityMapper;
		 this.activityRepository=activityRepository;
		 this.template=template;
	}

	@RabbitListener(queues = RabbitConfig.queueName)
	public void receiveMessage(Message message) {
		System.out.println("Received <" + message + ">");
		Activity activity=activityMapper.toEntityInsert(message);

		if (message.getActivityName().equals("Toileting") || message.getActivityName().equals("Leaving")
				|| message.getActivityName().equals("Showering") || message.getActivityName().equals("Sleeping")) {
			DateTime start = new DateTime(message.getStart());
			DateTime end = new DateTime(message.getEnd());
			Period period = new Period(start, end);
//			String notification="The client "+activity.getPatient().getName()+" was "+activity.getName()+" for "+period.getHours()+":"+period.getMinutes()+":"+period.getSeconds();
			String periodString=period.getHours()+":"+period.getMinutes()+":"+period.getSeconds();
			Notification notification=new Notification(activity.getPatient().getName(),periodString,activity.getName(),activity.getPatient().getId());
			//System.out.println("Normal:"+message.getActivityName() + ":" + period.getHours());
			if (message.getActivityName().equals("Toileting")
					&& (period.getHours() > 1 || (period.getHours() == 1 && (period.getMinutes() > 0 || period.getSeconds()>0)))) {
				System.out.println(message.getActivityName() + ":: " + period.getHours()+":"+period.getMinutes());
				template.convertAndSend("/topic",notification);

			}
			
			else if (message.getActivityName().equals("Showering")
					&& (period.getHours() > 1 || (period.getHours() == 1 && (period.getMinutes() > 0 || period.getSeconds()>0)))) {
				System.out.println(message.getActivityName() + ":: " + period.getHours()+":"+period.getMinutes());
				template.convertAndSend("/topic",notification);

			}
			
			else if (message.getActivityName().equals("Leaving")
					&& (period.getHours() > 12 || (period.getHours() == 12 && (period.getMinutes() > 0 || period.getSeconds()>0)))) {
				System.out.println(message.getActivityName() + ":: " + period.getHours()+":"+period.getMinutes());
				template.convertAndSend("/topic",notification);

			}
			
			else if (message.getActivityName().equals("Sleeping")
					&& (period.getHours() > 12 || (period.getHours() == 12 && (period.getMinutes() > 0 || period.getSeconds()>0)))) {
				System.out.println(message.getActivityName() + ":: " + period.getHours()+":"+period.getMinutes());
				template.convertAndSend("/topic",notification);

			}
			else {
				activity.setNormal(true);
			}
		}
		else {
			activity.setNormal(true);
		}
		activityRepository.save(activity);

		// latch.countDown();
//        try {
//			this.getLatch().await(10000, TimeUnit.MILLISECONDS);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

//    public CountDownLatch getLatch() {
//        return latch;
//    }

}