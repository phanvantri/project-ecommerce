import React, { Component } from 'react';
import abc from '../../img/m1.jpg';
import { Link, NavLink } from 'react-router-dom';
import {connect} from 'react-redux';
import propTypes from 'prop-types';
import {getAllCategory} from '../../actions/CategoryActions'
class ListCategory extends Component {
    componentDidMount(){
        this.props.getAllCategory();
    }
    

    render() {
        const {project_Category}=this.props.project_Category;
        let BoardContent;
        let listCategorys = [];
     
    
        const BoardAlgorithm = project_Category => {
          if (project_Category.length < 1) {
            return (
              <div className="alert alert-info text-center" role="alert">
                Danh mục trống hông có gì cả
              </div>
            );
          } else {
            const categorys = project_Category.map(item => (
    
              <ItemCategory nameCategory={item.name} />
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
                             <img src={abc} alt="logo" />
                
                            <div className="men-cart-pro">
                                <div className="inner-men-cart-pro">
                                        <Link to ="/category" className="link-product-add-cart">Quick View</Link>
 
                                </div>
                            </div>
                    </div>
                    <h3 className="pt-1">{this.props.nameCategory}</h3>
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
