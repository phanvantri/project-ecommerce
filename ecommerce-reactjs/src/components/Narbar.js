import React, { Component } from 'react';
import {Link} from 'react-router-dom'
class Narbar extends Component {
    render() {
        return (
            
            <nav className="navbar navbar-expand-sm navbar-dark bg-primary mb-4">
            <div className="container">
              <Link to="/" className="navbar-brand" >
                Danh mục sản phẩm
              </Link>
              <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#mobile-nav">
                <span className="navbar-toggler-icon">
                </span></button>
            </div>
          </nav>
        );
    }
}

export default Narbar;