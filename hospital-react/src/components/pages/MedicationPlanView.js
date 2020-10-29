import React, { Component } from 'react';
import axios from 'axios';
import { Input, FormGroup, Label, Modal, ModalHeader, ModalBody, ModalFooter, Table, Button } from 'reactstrap';
import Cookie from "js-cookie";

class MedicationPlan {
    constructor(adress,birthDate,gender,id,name,password,role,username){
       this.adress=adress;
       this.birthDate=birthDate;
       this.gender=gender;
       this.id=id;
       this.name=name;
       this.password=password;
       this.role=role;
       this.username=username;
    }

}

class MedicationPlanView extends Component {


    constructor(props) {
        super(props);
        console.log(JSON.parse(localStorage.getItem("medicationPlan")))

        this.state = {
            drugs: [],
            drugsInMedicalPlan: [],

            newDrugData: {
                drugDTO:{
                    id:'',
                },
                startHour: '',
                finishHour: '',
            },
            editDrugData: {
                drugDTO:{
                    id:'',
                },
                startHour: '',
                finishHour: '',
            },
            newDrugModal: false,
            editDrugModal: false
        }
    }
    componentWillMount() {
        this._refreshIntakeInterval()
        this._refreshDrugs();
    }
    toggleNewDrugModal(drugId) {
        this.setState({
            newDrugData:{
              drugDTO:{
                  id:drugId
              }
            },
            newDrugModal: ! this.state.newDrugModal
        });
    }
    toggleEditDrugModal() {
        this.setState({
            editDrugModal: ! this.state.editDrugModal
        });
    }
    addIntakeInterval() {
        let {drugDTO, startHour,finishHour } = this.state.newDrugData;
        console.log(drugDTO)
        console.log(localStorage.getItem("medicationPlan"))
        let medicationPlanDTO=JSON.parse(localStorage.getItem("medicationPlan"));
        axios.post('http://localhost:8080/intakeInterval/',
            {
                drugDTO, startHour,finishHour,medicationPlanDTO
            },{headers: {
                'Accept':"application/json",
                'Content-Type':"application/json"
            }}).then((response) => {
            let { drugsInMedicalPlan } = this.state;
            let{drugs}=this.state;

            drugsInMedicalPlan.push(response.data);



            this.setState({ drugsInMedicalPlan,drugs, newDrugModal: false, newDrugData: {
                    drugDTO:{
                        id:'',
                    },
                    startHour: '',
                    finishHour: '',
                }});
            this._refreshDrugs()
        });
    }
    updateDrug() {
        let {drugDTO, startHour,finishHour } = this.state.editDrugData;
        let medicationPlanDTO=JSON.parse(localStorage.getItem("medicationPlan"));

        axios.put('http://localhost:8080/intakeInterval', {
            drugDTO,medicationPlanDTO,startHour,finishHour
        },{headers: {
                'Accept':"application/json",
                'Content-Type':"application/json",
                'Access-Control-Allow-Credentials':"*"
            }}).then((response) => {
            this.setState({
                editDrugModal: false, editDrugData: { drugDTO:{id: ''},finishHour: '',startHour: ''}
            })
            this._refreshIntakeInterval();
        });
    }
    editDrug(id, startHour,finishHour) {
        this.setState({
            editDrugData: { drugDTO:{id:id}, startHour,finishHour }, editDrugModal: ! this.state.editDrugModal
        });
    }
    deleteDrug(id) {
        axios.delete('http://localhost:8080/intakeInterval/',{headers: {
                'Accept':"application/json",
                'Content-Type':"application/json"
            },data:{drugDTO:{id:id},
                medicationPlanDTO:JSON.parse(localStorage.getItem("medicationPlan"))}}).then((response) => {
            this._refreshIntakeInterval();
            this._refreshDrugs();
        });
    }
    _refreshDrugs() {
        let medicationPlan=JSON.parse(localStorage.getItem("medicationPlan"));
        console.log(medicationPlan.id)
        axios.get('http://localhost:8080/drug/medicalplan/not/'+medicationPlan.id,{headers: {
                'Accept':"application/json",
                'Content-Type':"application/json",
            },
        }).then((response) => {
            console.log(response.data)
            this.setState({
                drugs: response.data
            })
        });
    }

    _refreshIntakeInterval(){
        let medicationPlan=JSON.parse(localStorage.getItem("medicationPlan"));
        console.log(medicationPlan.id)
        axios.get('http://localhost:8080/intakeInterval/'+medicationPlan.id,{headers: {
                'Accept':"application/json",
                'Content-Type':"application/json",
            },
        }).then((response)=>{
            console.log(response.data)
            this.setState({drugsInMedicalPlan:response.data})
        })

    }
    render() {
        if(Cookie.get("role")==="DOCTOR") {

            let drugs = this.state.drugs.map((drug) => {
                return (
                    <tr key={drug.id}>
                        <td>{drug.id}</td>
                        <td>{drug.name}</td>
                        <td>{drug.dossage}</td>
                        <td>{drug.sideEffects}</td>
                        <td>
                            <Button color="success" size="sm" className="mr-2"
                                    onClick={this.toggleNewDrugModal.bind(this, drug.id)}>Add</Button>
                        </td>
                    </tr>
                )
            });

            let drugsInMedicalPlan = this.state.drugsInMedicalPlan.map((drug) => {
                return (
                    <tr key={drug.drugDTO.id}>
                        <td>{drug.drugDTO.id}</td>
                        <td>{drug.drugDTO.name}</td>
                        <td>{drug.drugDTO.dossage}</td>
                        <td>{drug.drugDTO.sideEffects}</td>
                        <td>{drug.startHour}</td>
                        <td>{drug.finishHour}</td>
                        <td>
                            <Button color="success" size="sm" className="mr-2"
                                    onClick={this.editDrug.bind(this, drug.drugDTO.id, drug.startHour, drug.finishHour)}>Edit</Button>
                            <Button color="danger" size="sm"
                                    onClick={this.deleteDrug.bind(this, drug.drugDTO.id)}>Delete</Button>
                        </td>
                    </tr>
                )
            });
            return (
                <div className="App container">

                    <h1>Medical Plan</h1>

                    <Modal isOpen={this.state.editDrugModal} toggle={this.toggleEditDrugModal.bind(this)}>
                        <ModalHeader toggle={this.toggleEditDrugModal.bind(this)}>Edit a new drug</ModalHeader>
                        <ModalBody>

                            <FormGroup>
                                <Label for="id">Id</Label>
                                <Input id="id" value={this.state.editDrugData.drugDTO.id} onChange={(e) => {
                                    // let { editPatientData } = this.state;
                                    //
                                    // editPatientData.id = e.target.value;
                                    //
                                    // this.setState({ editPatientData });
                                }}/>
                            </FormGroup>


                            <FormGroup>
                                <Label for="startHour">Start Hour</Label>
                                <Input id="startHour" value={this.state.editDrugData.startHour} onChange={(e) => {
                                    let {editDrugData} = this.state;

                                    editDrugData.startHour = e.target.value;

                                    this.setState({editDrugData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="finishHour">Finish Hour</Label>
                                <Input id="finishHour" value={this.state.editDrugData.finishHour} onChange={(e) => {
                                    let {editDrugData} = this.state;

                                    editDrugData.finishHour = e.target.value;

                                    this.setState({editDrugData});
                                }}/>
                            </FormGroup>

                        </ModalBody>
                        <ModalFooter>
                            <Button color="primary" onClick={this.updateDrug.bind(this)}>Update Drug</Button>{' '}
                            <Button color="secondary" onClick={this.toggleEditDrugModal.bind(this)}>Cancel</Button>
                        </ModalFooter>
                    </Modal>


                    <Table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>Dossage</th>
                            <th>Side Effects</th>
                            <th>Start Hour</th>
                            <th>Finish Hour</th>
                        </tr>
                        </thead>

                        <tbody>
                        {drugsInMedicalPlan}
                        </tbody>
                    </Table>


                    <Modal isOpen={this.state.newDrugModal} toggle={this.toggleNewDrugModal.bind(this)}>
                        <ModalHeader toggle={this.toggleNewDrugModal.bind(this)}>Add a new Drog</ModalHeader>
                        <ModalBody>

                            <FormGroup>
                                <Label for="id">Id</Label>
                                <Input id="id" value={this.state.newDrugData.drugDTO.id} onChange={(e) => {
                                }}/>
                            </FormGroup>


                            <FormGroup>
                                <Label for="startHour">Start Hour</Label>
                                <Input id="startHour" value={this.state.newDrugData.startHour} onChange={(e) => {
                                    let {newDrugData} = this.state;

                                    newDrugData.startHour = e.target.value;

                                    this.setState({newDrugData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="finishHour">Finish Hour</Label>
                                <Input id="finishHour" value={this.state.newDrugData.finishHour} onChange={(e) => {
                                    let {newDrugData} = this.state;

                                    newDrugData.finishHour = e.target.value;

                                    this.setState({newDrugData});
                                }}/>
                            </FormGroup>

                        </ModalBody>
                        <ModalFooter>
                            <Button color="primary" onClick={this.addIntakeInterval.bind(this)}>Add Drug</Button>{' '}
                            <Button color="secondary" onClick={this.toggleNewDrugModal.bind(this)}>Cancel</Button>
                        </ModalFooter>
                    </Modal>


                    <Table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>Dossage</th>
                            <th>Side Effects</th>
                        </tr>
                        </thead>

                        <tbody>
                        {drugs}
                        </tbody>
                    </Table>

                </div>
            );
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

export default MedicationPlanView;