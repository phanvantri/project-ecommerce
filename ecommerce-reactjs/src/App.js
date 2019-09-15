import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Narbar from './components/Narbar';
import Category from './components/Category';

function App() {
  return (
    <div className="App">
      <Narbar></Narbar>
      <Category></Category>
    </div>
  );
}

export default App;
