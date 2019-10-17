import React, { Component } from 'react';

class Discount extends Component {

    render() {
        return (
            <div className="col-lg-3 mt-lg-0 mt-4 p-lg-0">
            <div className="side-bar p-sm-4 p-3">
              <div className="search-hotel border-bottom py-2">
                <h3 className="agileits-sear-head mb-3">Tìm kiếm...</h3>
                <form action="#" method="post">
                  <input type="search" placeholder="Product name..." name="search" required />
                  <input type="submit" defaultValue=" " />
                </form>
              </div>
              {/* price */}
              <div className="range border-bottom py-2">
                <h3 className="agileits-sear-head mb-3">Giá</h3>
                <div className="w3l-range">
                  <ul>
                    <li>
                      <a href="#">Dưới 100,000</a>
                    </li>
                    <li className="my-1">
                      <a href="#">$100,000 - 200,000</a>
                    </li>
                    <li>
                      <a href="#">200,000 - 300,000</a>
                    </li>
                    <li className="my-1">
                      <a href="#">300,000 - 400,000</a>
                    </li>
                    <li>
                      <a href="#">400,000 - 500,000</a>
                    </li>
                    <li className="mt-1">
                      <a href="#">Trên 500,000</a>
                    </li>
                  </ul>
                </div>
              </div>
              {/* //price */}
              {/* discounts */}
              <div className="left-side border-bottom py-2">
                <h3 className="agileits-sear-head mb-3">Discount</h3>
                <ul>
                  <li>
                    <input type="checkbox" className="checked" />
                    <span className="span">5% or More</span>
                  </li>
                  <li>
                    <input type="checkbox" className="checked" />
                    <span className="span">10% or More</span>
                  </li>
                  <li>
                    <input type="checkbox" className="checked" />
                    <span className="span">20% or More</span>
                  </li>
                  <li>
                    <input type="checkbox" className="checked" />
                    <span className="span">30% or More</span>
                  </li>
                  <li>
                    <input type="checkbox" className="checked" />
                    <span className="span">50% or More</span>
                  </li>
                  <li>
                    <input type="checkbox" className="checked" />
                    <span className="span">60% or More</span>
                  </li>
                </ul>
              </div>
            </div>
            {/* //product right */}
          </div>
        );
    }
}

export default Discount;