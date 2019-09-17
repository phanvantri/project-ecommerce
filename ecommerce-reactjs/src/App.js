import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Narbar from './components/Narbar';
import Category from './components/Category';

import {BrowserRouter as Router, Route} from 'react-router-dom';
import AddCategory from './components/category/AddCategory';
import UpdateCategory from './components/category/UpdateCategory';
//Connect Redux
import {Provider} from 'react-redux';
import store from './store';

function App() {
  return (
    <Provider store={store}>
        <Router>
        <div className="App">
          <Narbar></Narbar>
          <Route exact path="/" component={Category}/>
          <Route exact path="/addCategory" component={AddCategory}/>
          <Route exact path="/updateCategory" component={UpdateCategory}/>
        </div>
        </Router>
    </Provider>
  );
}

export default App;
