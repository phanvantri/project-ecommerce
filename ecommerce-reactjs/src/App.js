import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter as Router, Route} from 'react-router-dom';
//Connect Redux
import {Provider} from 'react-redux';
import store from './store';

//login
import { ACCESS_TOKEN } from './constants';
import { getCurrentUser } from './utils/APIUtils';
import LoadingIndicator from './common/LoadingIndicator';
import AppProject from './app/AppProject';


function App() {
  return (
    <Provider store={store}>
        <Router>
        <div className="App">
              <AppProject></AppProject>
        </div>
        </Router>
    </Provider>
  );
}

export default App;
