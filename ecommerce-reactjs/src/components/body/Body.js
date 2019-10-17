import React, { Component } from 'react';
import Login from '../login/Login';
import Category from '../Category';
import AddCategory from '../category/AddCategory';
import UpdateCategory from '../category/UpdateCategory';
import Signup from '../signup/Signup';
import Banner from '../banner/Banner';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import ListCategory from '../category/ListCategory';
import ListProductNew from '../product/ListProductNew';
import ListProduct from '../product/ListProduct';

class Body extends Component {

    render() {
        return (
            <div>
            
                <Route exact path="/" component={Banner}/>
                <Route exact path="/" component={ListCategory}/>   
                <Route exact path="/" component={ListProduct}/>        
                <Route exact path="/Login" component={Login}/>
                <Route exact path="/category" component={Category}/>
                <Route exact path="/addCategory" component={AddCategory}/>
                <Route exact path="/updateCategory" component={UpdateCategory}/>
                <Route exact path="/signup" component={Signup}/>
            </div>
        );
    }
}

export default Body;