import React, { Component } from 'react';
import { Link, NavLink } from 'react-router-dom';

class Nar extends Component {
    render() {
        return (
            <div className="agile-main-top">
            <div className="container-fluid">
              <div className="row main-top-w3l py-2">
                <div className="col-lg-4 header-most-top">
                  <p className="text-white text-lg-left text-center">Shopee
                    <i className="fas fa-shopping-cart ml-1" />
                  </p>
                </div>
                <div className="col-lg-8 header-right mt-lg-0 mt-2">
                  <ul>
                    <li className="text-center border-right text-white">
                      <a className="play-icon popup-with-zoom-anim text-white" href="#small-dialog1">
                        <i className="fas fa-map-marker mr-2" />Chọn vị trí</a>
                    </li>
                    <li className="text-center border-right text-white">
                      <a href="#" data-toggle="modal" data-target="#exampleModal" className="text-white">
                        <i className="fas fa-truck mr-2" />Giao hàng</a>
                    </li>
                    <li className="text-center border-right text-white">
                      <i className="fas fa-phone mr-2" /> 038 505 3517
                    </li>
                    <li className="text-center border-right text-white">
                      <NavLink to='/Login' data-toggle="modal" data-target="#exampleModal" className="text-white">
                        <i className="fas fa-sign-in-alt mr-2" /> Đăng nhập </NavLink>
                    </li>
                    <li className="text-center text-white">
                      <NavLink to='/signup' data-toggle="modal" data-target="#exampleModal2" className="text-white">
                        <i className="fas fa-sign-out-alt mr-2" />Đăng kí </NavLink>
                    </li>
                  </ul>
                  {/* //header lists */}
                </div>
              </div>
            </div>
          </div>
          
        );
    }
}

export default Nar;