import React, { Component } from 'react';
import Nar from '../components/nar/Nar';
import Header from '../components/header/Header';
import Menu from '../components/menu/Menu';
import Body from '../components/body/Body';
import Footer from '../components/footer/Footer';
import Signup from '../components/user/signup/Signup';
import Login from '../components/user/login/Login';
//login
import { Link, NavLink } from 'react-router-dom';
import Alert from 'react-s-alert';
import { ACCESS_TOKEN } from '../constants';
import { getCurrentUser } from '../utils/APIUtils';
import LoadingIndicator from '../common/LoadingIndicator';
import PrivateRoute from '../common/PrivateRoute';
import OAuth2RedirectHandler from '../components/user/oauth2/OAuth2RedirectHandler';
import {
  Route,
  Switch
} from 'react-router-dom';
import Profile from '../components/user/profile/Profile';

class AppProject extends Component {
    constructor(props) {
        super(props);
        this.state = {
          authenticated: false,
          currentUser: null,
          loading: false
        }
        this.loadCurrentlyLoggedInUser = this.loadCurrentlyLoggedInUser.bind(this);
        this.handleLogout = this.handleLogout.bind(this);
      }
      loadCurrentlyLoggedInUser() {
        
        this.setState({
          loading: true
        });
    
        getCurrentUser()
          .then(response => {
          
            this.setState({
              currentUser: response,
              authenticated: true,
              loading: false
            });
          }).catch(error => {
          
            this.setState({
              loading: false
            });  
          });    
      }
    
      handleLogout() {
        localStorage.removeItem(ACCESS_TOKEN);
        this.setState({
          authenticated: false,
          currentUser: null
        });
        Alert.success("You're safely logged out!");
      }
    
      componentDidMount() {
        this.loadCurrentlyLoggedInUser();
      }
    render() {
        if(this.state.loading) {
            return <LoadingIndicator />
          }
        return (
            <div>
                <Nar authenticated={this.state.authenticated} onLogout={this.handleLogout}></Nar>
                <Switch>
                    <PrivateRoute path="/profile" authenticated={this.state.authenticated} currentUser={this.state.currentUser}
                    component={Profile}></PrivateRoute>
            
                    <Route path="/login"
                       render={(props) => <Login authenticated={this.state.authenticated} {...props} />}></Route>
                   <Route path="/signup"
                        render={(props) => <Signup authenticated={this.state.authenticated} {...props} />}></Route>
                  <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}></Route>  
                </Switch>
                <Header></Header>
                <Menu></Menu>
                <Body></Body>
                <Footer></Footer>
            </div>
        );
    }
}

export default AppProject;