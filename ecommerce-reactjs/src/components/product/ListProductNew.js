//danh sách sản phẩm mới
import React, { Component } from 'react';
import abc from '../../img/m1.jpg';
import { Link, NavLink } from 'react-router-dom';
import Discount from './Discount';
import {connect} from 'react-redux';
import propTypes from 'prop-types';
import {getProductNew} from '../../actions/ProductAction';
import Pagination from "react-js-pagination";
class ListProductNew extends Component {
  componentDidMount(){
    this.props.getProductNew(); //gọi tới function của ProductNewAction
  }

    render() {
      const {product_new}=this.props.product_new;
      console.log(product_new);
      let lstProductnews=[];
      const Product = product_new =>{
        if(product_new.length < 1){
          return (
            <div className="alert alert-info text-center" role="alert">
              Sản phẩm trống hông có gì cả
            </div>
          );
        }
        else{
          const lstproduct_new=product_new.map(item=>(
            <ItemProduct product={item}></ItemProduct>

          ));
          for (let i = 0; i < lstproduct_new.length; i++) {
            lstProductnews.push(lstproduct_new[i]);
        }
        }
      };
      Product(product_new);
        return (
      
                                                <div className="product-sec1 px-sm-4 px-3 py-sm-5  py-3 mb-4">
                                                <h3 class="heading-tittle text-center font-italic">Sản phẩm mới</h3>
                                                    <div className="row">
                                                        {lstProductnews}
                                                    </div>
                                                </div>
                                      
  
           
        );
    }
}

class ItemProduct extends Component{
    render(){
        return(
            <div className="col-md-4 product-men mt-5">
            <div className="men-pro-item simpleCart_shelfItem">
              <div className="men-thumb-item text-center">
              <img src={abc} alt="logo" />
                <div className="men-cart-pro">
                  <div className="inner-men-cart-pro">
                    <a href="single.html" className="link-product-add-cart">Xem chi tiết</a>
                  </div>
                </div>
                <span className="product-new-top">New</span>
              </div>
              <div className="item-info-product text-center border-top mt-4">
                <h4 className="pt-1">
                  <a href="single.html">{this.props.product.name}</a>
                </h4>
                <div className="info-product-price my-2">
                  <span className="item_price">{this.props.product.product_details.pricesale} đồng.</span>
                  <del>{this.props.product.product_details.price}</del>
                </div>
                <div className="snipcart-details top_brand_home_details item_add single-item hvr-outline-out">
                  <form action="#" method="post">
                    <fieldset>
                      <input type="hidden" name="cmd" defaultValue="_cart" />
                      <input type="hidden" name="add" defaultValue={1} />
                      <input type="hidden" name="business" defaultValue=" " />
                      <input type="hidden" name="item_name" defaultValue="OPPO A37f" />
                      <input type="hidden" name="amount" defaultValue={230.00} />
                      <input type="hidden" name="discount_amount" defaultValue={1.00} />
                      <input type="hidden" name="currency_code" defaultValue="USD" />
                      <input type="hidden" name="return" defaultValue=" " />
                      <input type="hidden" name="cancel_return" defaultValue=" " />
                      <input type="submit" name="submit" value="Thêm giỏ hàng" className="button btn" />
                    </fieldset>
                  </form>
                </div>
              </div>
            </div>
          </div>
        );
    }
}
ListProductNew.propTypes={
  getProductNew:propTypes.func.isRequired// check function thoi ko quang trong

}
const mapStateToProps =state =>({
  product_new:state.product_new
})

export default connect(mapStateToProps,{getProductNew}) (ListProductNew);  //connect ket noi toi store lay cai getProductNew gán vào mapStateToProps