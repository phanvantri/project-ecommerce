import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import {BrowserRouter as Router, Route} from 'react-router-dom';
import Headers from './components/header/Header';
import Nar from './components/nar/Nar'
//Connect Redux
import {Provider} from 'react-redux';
import store from './store';
//
import Menu from './components/menu/Menu'
import Banner from './components/banner/Banner';
import Footer from './components/footer/Footer';
import Body from './components/body/Body';


function App() {
  return (
    <Provider store={store}>
        <Router>
        <div className="App">
        <Nar></Nar>
        <Headers></Headers>
        <Menu></Menu>
        <Body></Body>
        
        <Footer></Footer>
        </div>
        </Router>
    </Provider>
  );
}

export default App;
