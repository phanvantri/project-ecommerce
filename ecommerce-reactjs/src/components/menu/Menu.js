import React, { Component } from 'react';

class Menu extends Component {

    render() {
        return (
            <div className="navbar-inner">
            <div className="container">
              <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="agileits-navi_search">
                  <form action="#" method="post">
                    <select id="agileinfo-nav_search" name="agileinfo_search" className="border" required>
                      <option value>Tất cả danh mục</option>
                      <option value="Televisions">Televisions</option>
                      <option value="Headphones">Headphones</option>
                      <option value="Computers">Computers</option>
                      <option value="Appliances">Appliances</option>
                      <option value="Mobiles">Mobiles</option>
                      <option value="Fruits & Vegetables">Tv &amp; Video</option>
                      <option value="iPad & Tablets">iPad &amp; Tablets</option>
                      <option value="Cameras & Camcorders">Cameras &amp; Camcorders</option>
                      <option value="Home Audio & Theater">Home Audio &amp; Theater</option>
                    </select>
                  </form>
                </div>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span className="navbar-toggler-icon" />
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul className="navbar-nav ml-auto text-center mr-xl-5">
                    <li className="nav-item active mr-lg-2 mb-lg-0 mb-2">
                      <a className="nav-link" href="index.html">Trang chủ
                        <span className="sr-only">(current)</span>
                      </a>
                    </li>
                   
                    <li className="nav-item dropdown mr-lg-2 mb-lg-0 mb-2">
                      <a className="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Khuyến mãi
                      </a>
                      <div className="dropdown-menu">
                        <div className="agile_inner_drop_nav_info p-4">
                          <h5 className="mb-3">TV, Appliances, Electronics</h5>
                          <div className="row">
                            <div className="col-sm-6 multi-gd-img">
                              <ul className="multi-column-dropdown">
                                <li>
                                  <a href="product2.html">Televisions</a>
                                </li>
                                <li>
                                  <a href="product2.html">Home Entertainment Systems</a>
                                </li>
                                <li>
                                  <a href="product2.html">Headphones</a>
                                </li>
                                <li>
                                  <a href="product2.html">Speakers</a>
                                </li>
                                <li>
                                  <a href="product2.html">MP3, Media Players &amp; Accessories</a>
                                </li>
                                <li>
                                  <a href="product2.html">Audio &amp; Video Accessories</a>
                                </li>
                                <li>
                                  <a href="product2.html">Cameras</a>
                                </li>
                                <li>
                                  <a href="product2.html">DSLR Cameras</a>
                                </li>
                                <li>
                                  <a href="product2.html">Camera Accessories</a>
                                </li>
                              </ul>
                            </div>
                            <div className="col-sm-6 multi-gd-img">
                              <ul className="multi-column-dropdown">
                                <li>
                                  <a href="product2.html">Musical Instruments</a>
                                </li>
                                <li>
                                  <a href="product2.html">Gaming Consoles</a>
                                </li>
                                <li>
                                  <a href="product2.html">All Electronics</a>
                                </li>
                                <li>
                                  <a href="product2.html">Air Conditioners</a>
                                </li>
                                <li>
                                  <a href="product2.html">Refrigerators</a>
                                </li>
                                <li>
                                  <a href="product2.html">Washing Machines</a>
                                </li>
                                <li>
                                  <a href="product2.html">Kitchen &amp; Home Appliances</a>
                                </li>
                                <li>
                                  <a href="product2.html">Heating &amp; Cooling Appliances</a>
                                </li>
                                <li>
                                  <a href="product2.html">All Appliances</a>
                                </li>
                              </ul>
                            </div>
                          </div>
                        </div>
                      </div>
                    </li>
                    <li className="nav-item mr-lg-2 mb-lg-0 mb-2">
                      <a className="nav-link" href="about.html">Thông tin về chúng tôi</a>
                    </li>
                   
                    <li className="nav-item">
                      <a className="nav-link" href="contact.html">Liên hệ</a>
                    </li>
                  </ul>
                </div>
              </nav>
            </div>
          </div>
        );
    }
}

export default Menu;