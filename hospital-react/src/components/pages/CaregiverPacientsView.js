import * as React from "react";
import axios from "axios";
import {Button, Table} from "reactstrap";
import Cookie from "js-cookie";


export default class CaregiverPacientsView extends React.Component{

    state = {
        patientsInList:[],
        medicationPlanList:[]

    }

    componentDidMount() {
        this._refreshPatientsFromList()

    }

    _refreshPatientsFromList(){
        console.log(Cookie.get("userId"))
        axios.get('http://localhost:8080/caregiverHasPatient/'+parseInt(Cookie.get("userId"))).then((response) => {


            this.setState({
                patientsInList: response.data
            })
        });

    }

    viewMedicationPlan(patientDTOID){
        axios.get('http://localhost:8080/medicationPlan/'+patientDTOID

        ).then((response) => {
            this.setState({medicationPlanList: response.data})
            console.log(response.data)
            console.log(this.state.medicationPlanList)
        });

    }

    viewIntervalIntakeForMedicationPlan(medicationPlanId){
        Cookie.set("medicationPlanId",medicationPlanId)
        window.location.href = "http://localhost:3000/medicationplans/intervalIntake"
    }

    render() {

        if(Cookie.get("role")==="CAREGIVER") {
            let patients = this.state.patientsInList.map((patientHasCaregiver) => {
                return (

                    <tr key={patientHasCaregiver.patientDTO.id}>
                        <td>{patientHasCaregiver.patientDTO.id}</td>
                        <td>{patientHasCaregiver.patientDTO.name}</td>
                        <td>{patientHasCaregiver.patientDTO.birthDate}</td>
                        <td>{patientHasCaregiver.patientDTO.gender}</td>
                        <td>{patientHasCaregiver.patientDTO.adress}</td>
                        <td>{patientHasCaregiver.patientDTO.username}</td>
                        <td>{patientHasCaregiver.patientDTO.password}</td>
                        <td>{patientHasCaregiver.patientDTO.role}</td>
                        <td>
                            <Button color="success" size="sm" className="mr-2"
                                    onClick={this.viewMedicationPlan.bind(this, patientHasCaregiver.patientDTO.id)}>View
                                Medication Plan</Button>
                        </td>
                    </tr>

                )
            });
            let medicationPlans = this.state.medicationPlanList.map((medicationPlan) => {
                return (


                    <tr key={medicationPlan.id}>
                        <td>{medicationPlan.id}</td>
                        <td>{medicationPlan.doctorDTO.id}</td>
                        <td>{medicationPlan.doctorDTO.name}</td>
                        <td>{medicationPlan.patientDTO.id}</td>
                        <td>{medicationPlan.patientDTO.name}</td>
                        <td>
                            <Button color="success" size="sm" className="mr-2"
                                    onClick={this.viewIntervalIntakeForMedicationPlan.bind(this, medicationPlan.id)}>View
                                Drugs Interval</Button>
                        </td>
                    </tr>

                )
            });

            return (<div className="App container">
                <h1>Drugs App</h1>
                <Table>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Birth Date</th>
                        <th>Gender</th>
                        <th>Adress</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Role</th>
                    </tr>
                    </thead>

                    <tbody>
                    {patients}
                    </tbody>
                </Table>
                <h1>Medication Plans</h1>
                <Table>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Id Doctor</th>
                        <th>Nume Doctor</th>
                        <th>Id Pacient</th>
                        <th>Nume Pacient</th>
                    </tr>
                    </thead>

                    <tbody>
                    {medicationPlans}
                    </tbody>
                </Table>
            </div>);
        }
        else{
            return (
             <div>
                 <h1> Nu aveti acces la aceasta pagina</h1>
             </div>
            );
        }


        }

}