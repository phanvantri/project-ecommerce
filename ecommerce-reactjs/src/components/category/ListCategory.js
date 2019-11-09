import React, { Component } from 'react';
import abc from '../../img/m1.jpg';
import { Link, NavLink } from 'react-router-dom';
import {connect} from 'react-redux';
import propTypes from 'prop-types';
import {getAllCategory} from '../../actions/CategoryActions';
import Pagination from "react-js-pagination";
class ListCategory extends Component {

      constructor(props) {
        super(props);
        this.state = {
          activePage:1    
        };
        this.fetchURL = this.fetchURL.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
      
    }
    fetchURL(page) {
      this.props.getAllCategory(page);
    }
    handlePageChange(pageNumber) {
      console.log(`active page is ${pageNumber}`);
      this.setState({activePage: pageNumber})
      this.fetchURL(pageNumber)
     
      }
    componentDidMount(){
      this.fetchURL(this.state.activePage);
    }
    

    render() {
        const {project_Category}=this.props.project_Category;
        const lst=project_Category.content;
        let BoardContent;
        let listCategorys = [];
        const totalPages=project_Category.totalPages;
        const itemsCountPerPage=project_Category.size;
        const totalItemsCount=project_Category.totalElements;
  
     
    
        const BoardAlgorithm = project_Category => {
          if (project_Category.length < 1) {
            return (
              <div className="alert alert-info text-center" role="alert">
                Danh mục trống hông có gì cả
              </div>
            );
          } else {
            const categorys = lst.map(item => (
    
              <ItemCategory category={item} />
            ));
            for (let i = 0; i < categorys.length; i++) {
                listCategorys.push(categorys[i]);
            }
           
          }
        };
    
        BoardAlgorithm(project_Category);
        return (
            <div className="ads-grid py-sm-5 py-4">
                 <div className="product-sec1 px-sm-4 px-3 py-sm-5  py-3 mb-4">
                          <h3 className="heading-tittle text-center font-italic">Danh mục</h3>
                            <div className="row">
                                
                                {listCategorys}
                                                 
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
             </div>
        );
    }
}

class ItemCategory extends Component{
    render(){
    
        return (
        
           
            <div className="col-md-3 product-men mt-5">
                 <NavLink to="/category"className="men-pro-item simpleCart_shelfItem">
                     <div className="men-thumb-item text-center">
                             <img src={this.props.category.linkimage} alt="logo"  className="fixed_img"/>

                
                            <div className="men-cart-pro">
                                <div className="inner-men-cart-pro">
                                        <Link to ="/category" className="link-product-add-cart">Quick View</Link>
 
                                </div>
                            </div>
                    </div>
                    <h8 className="pt-1">{this.props.category.name}</h8>
                </NavLink>
            </div>
        );
    }
}
ListCategory.propTypes={
    getAllCategory:propTypes.func.isRequired,
    project_Category:propTypes.object.isRequired
}

const mapStateToProps = state =>({
    project_Category: state.project_Category
})
export default connect(mapStateToProps,{getAllCategory})(ListCategory);
