//danh sách sản phẩm mới
import React, { Component } from 'react';
import abc from '../../img/m1.jpg';
import { Link, NavLink } from 'react-router-dom';
import Discount from './Discount';
import {connect} from 'react-redux';
import propTypes from 'prop-types';
import {getAllProduct} from '../../actions/ProductAction';
import Pagination from "react-js-pagination";
class Product extends Component {

    constructor(props) {
        super(props);
        this.state = {
          activePage:1    
        };
        this.fetchURL = this.fetchURL.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
       
    }
    fetchURL(page) {
      this.props.getAllProduct(page);
    }

    handlePageChange(pageNumber) {
      console.log(`active page is ${pageNumber}`);
      this.setState({activePage: pageNumber})
      this.fetchURL(pageNumber)
     
      }

    componentDidMount(){
       // this.props.getAllProduct(); //gọi tới function của ProductNewAction
       this.fetchURL(this.state.activePage)
    }

    render() {
      const {product}=this.props.product;
      
      console.log({product});
      let lstProduct=[];
      const lst=product.content;
      const totalPages=product.totalPages;
      const itemsCountPerPage=product.size;
      const totalItemsCount=product.totalElements;

      const ProductList = product =>{
        if(product.length < 1){
          return (
            <div className="alert alert-info text-center" role="alert">
              Sản phẩm trống hông có gì cả
            </div>
          );
        }
        else{           
            const lstP=lst.map(item=>(
                <ItemProduct product={item}></ItemProduct>
           
              ));
              for (let i = 0; i < lstP.length; i++) {
                lstProduct.push(lstP[i]);
              
            }
           
        }
      };
      ProductList(product);
     
     
        return (
      
                                                <div className="product-sec1 px-sm-4 px-3 py-sm-5  py-3 mb-4">
                                                <h3 class="heading-tittle text-center font-italic">Sản phẩm</h3>
                                                    <div className="row">
                                                        {lstProduct}
                                                    </div>
                                                    <div className="row">
                                                       
                                                    </div>
                                                    

                                                    <div className="d-flex justify-content-center">
                                                    <Pagination
                                                    hideNavigation
                                                    activePage={this.state.activePage}
                                                    itemsCountPerPage={itemsCountPerPage}
                                                    totalItemsCount={totalItemsCount}
                                                    pageRangeDisplayed={10}
                                                    itemClass='page-item'
                                                    linkClass='btn btn-light'
                                                    onChange={this.handlePageChange}
                                                    />
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
              <img src={this.props.product.imagephoto} alt="logo" className="fixed_img_product"/>
                <div className="men-cart-pro">
                  <div className="inner-men-cart-pro">
                    <NavLink to={`/product/${this.props.product.id}`} className="link-product-add-cart">Xem chi tiết</NavLink>
                  </div>
                </div>
                <span className="product-new-top">New</span>
              </div>
              <div className="item-info-product text-center border-top mt-4">
                <h6 className="pt-1">
                  <a href="single.html">{this.props.product.name}</a>
                </h6>
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
Product.propTypes={
    getAllProduct:propTypes.func.isRequired// check function thoi ko quang trong

}
const mapStateToProps =state =>({
  product:state.product
})

export default connect(mapStateToProps,{getAllProduct}) (Product);  //connect ket noi toi store lay cai getProductNew gán vào mapStateToProps