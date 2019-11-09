import React, { Component } from 'react';
import propTypes from 'prop-types';
import {connect} from 'react-redux';
import {getproductDetail} from '../../actions/ProductAction';
class ProductDetail extends Component {

  componentDidMount(){
    //alert( this.props.match.params.id);
    this.props.getproductDetail(this.props.match.params.id);
  }

  render() 
  {
      const {productdetail}=this.props.productdetail;//phai gion ten 2 bien va giong voi ten ben then index.js
      console.log({productdetail}); 
    return (
      <div className="banner-bootom-w3-agileits py-5">
        <div className="container py-xl-4 py-lg-2">
          {/* tittle heading */}
          <h3 className="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
            <span>Chi tiết sản phẩm</span></h3>
          {/* //tittle heading */}
          <div className="row">
            <div className="col-lg-5 col-md-8 single-right-left ">
              <div className="grid images_3_of_2">
                <div className="flexslider">
                  <ul className="slides">
                    <li data-thumb={productdetail.imagephoto}>
                      <div className="thumb-image">
                        <img src={productdetail.imagephoto} data-imagezoom="true" className="img-fluid" alt="" /> </div>
                    </li>
                   
                  </ul>
                  <div className="row">
                      <div className="col-md-4">
                        <div className="fixed_img_product">
                        <img src={productdetail.product_details&&productdetail.product_details.lstImage[0].name} data-imagezoom="true" className="img-fluid" alt="" /> </div>
          
                      </div>
                      <div className="col-md-4">
                        <div className="fixed_img_product">
                          <img src={productdetail.product_details&&productdetail.product_details.lstImage[1].name} data-imagezoom="true" className="img-fluid" alt="" /> </div>
                      
                        </div>
                      <div className="col-md-4">
                        <div className="fixed_img_product">
                          <img src={productdetail.product_details&&productdetail.product_details.lstImage[2].name} data-imagezoom="true" className="img-fluid" alt="" /> </div>
                    
                      </div>
                   
                     
                    
                      
                  </div>
                  <div className="clearfix" />
                </div>
              </div>
            </div>
            <div className="col-lg-7 single-right-left simpleCart_shelfItem">
                <div className="row">
                  <h3 className="mb-33">{productdetail.name}</h3>
                </div>
                <div className="row">
                    <p className="mb-33">
                    <span className="item_price">{productdetail.product_details&&productdetail.product_details.pricesale}</span>
                    <del className="mx-2 font-weight-light">{productdetail.product_details&&productdetail.product_details.price}</del>
                    <label>đồng.</label>
                  </p>
                </div>
                
              <div className="product-single-w3l">
                <div className="row">
                <p className="my-3">
                  <i className="far fa-hand-point-right mr-2" />
                  <label> {productdetail.product_details&&productdetail.product_details.description}</label></p>
                </div>
               
                <ul>
                  <li className="mb-33">
                    3 GB RAM | 16 GB ROM | Expandable Upto 256 GB
                  </li>
                  <br/>
                  <li className="mb-33">
                    5.5 inch Full HD Display
                  </li>
                  <br/>
                  <li className="mb-33">
                    13MP Rear Camera | 8MP Front Camera
                  </li>
                  <br/>
                  <li className="mb-33">
                    3300 mAh Battery
                  </li>
                  <br/>
                
                </ul>
                <br/>
                <div className="row">
                  
                       <p>Số lượng: </p><input type="number" className="text-box-fix" defaultValue={1} min={1} />
                  
                   
                </div>
              </div>
              <br/><br/>
              <div className="row">
              <div className="col-md-6">
                </div>
                  <div className="col-md-3">
                  <div className="occasion-cart">
                    <div className="snipcart-details top_brand_home_details item_add single-item hvr-outline-out">
                        <button type="button" class="btn btn-warning">Mua ngay</button>
                    
                    </div>
                  </div>
                  </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
ProductDetail.propTypes={
  getproductDetail:propTypes.func.isRequired// check function thoi ko quang trong

}
const mapStateToProps =state =>({
  productdetail:state.productdetail //lay du lieu tu index.js có productdetail
})


export default connect(mapStateToProps,{getproductDetail}) (ProductDetail);