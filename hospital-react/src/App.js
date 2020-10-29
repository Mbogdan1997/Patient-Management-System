import React, { Component } from 'react';
import './App.css';
import Meniu from "./Meniu"
import Login from "./components/pages/Login";
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import PatientsView from "./components/pages/PatientsView";
import DrugsView from "./components/pages/DrugsView";
import MedicationPlanView from "./components/pages/MedicationPlanView";
import CaregiversView from "./components/pages/CaregiverView";
import CaregiverHasPatientView from "./components/pages/CaregiverHasPatientView";
import CaregiverPacientsView from "./components/pages/CaregiverPacientsView";
import IntervalIntakeView from "./components/pages/IntervalIntakeView";
import UserHome from "./components/pages/UserHome";
import MeniuDiferentiat from "./components/MeniuDiferentiat";
import Logout from "./Logout";
import CaregiverNotification from "./CaregiverNotification";


class App extends Component {

    state={
        autentificat:false
    }

    constructor(props) {
        super(props)

        this.changeAutentificat = this.changeAutentificat.bind(this)
    }

    changeAutentificat(someValue) {
        this.setState({
            autentificat: someValue
        })
    }
  render() {
    return (
        <div className="MyApp">
        <CaregiverNotification/>
        <MeniuDiferentiat/>
          <Router>
              <div>
                  <Route
                      exact
                      path='/login'
                      render={() => <Login changeAutentificat={this.changeAutentificat}/>}
                  />
                  <Route
                      exact
                      path='/patient'
                      render={() => <PatientsView/>}
                  />
                  <Route
                  exact
                  path='/drugs'
                  render={() => <DrugsView/>}
              />
                  <Route
                      exact
                      path='/patient/medicationplans'
                      render={() => <MedicationPlanView/>}
                  />
                  <Route
                      exact
                      path='/caregiver'
                      render={() => <CaregiversView/>}
                  />
                  <Route
                      exact
                      path='/caregiver/viewPatients'
                      render={() => <CaregiverHasPatientView/>}
                  />
                  <Route
                      exact
                      path='/caregiver/viewMyPatients'
                      render={() => <CaregiverPacientsView/>}
                  />
                  <Route
                      exact
                      path='/medicationplans/intervalIntake'
                      render={() => <IntervalIntakeView/>}
                  />
                  <Route
                      exact
                      path='/user/home'
                      render={() => <UserHome/>}
                  />
                  <Route
                  exact
                  path='/logout'
                  render={()=><Logout changeAutentificat={this.changeAutentificat}/>}
                  />
              </div>
          </Router>
        </div>
    );
  }
}

export default App;