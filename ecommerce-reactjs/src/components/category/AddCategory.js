import React, { Component } from 'react';

/*Thuw viện giúp kết nối qua CategoryAction.js để bắt đến back-end CỤ THỂ LÀ actions CategoryActions.js*/
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import { addCategory} from '../../actions/CategoryActions';
import classnames from 'classnames';
/* */


class AddCategory extends Component {
    constructor(){
        super()
        this.state={
            nameCategory:"",  
            note:"",
            status:"",
            dateAdd:"",
            dateUpdate:"",
            errors:{}
        };
        this.onChange=this.onChange.bind(this);
        this.onSubmit=this.onSubmit.bind(this);
    }
    //Bắt sự kiện xảy ra lỗi nếu không có tên danh mục
    componentWillReceiveProps(nextProps){

      if(nextProps.errors){
        this.setState({errors: nextProps.errors});
      }
    }
     /* Bắt sự kiện thay đổi giá trị input */
    onChange(e){
        this.setState({ [e.target.name]:e.target.value})
    }
    /* Bắt sự kiện khi click submit */

    onSubmit(e){
        e.preventDefault()
        const newCategory={
            nameCategory:this.state.nameCategory,  
            note:this.state.note,
            status:this.state.status,
            dateAdd:"2011-08-21 14:11:09",
            dateUpdate:"2011-08-21 14:11:09"
        }
        //console.log(newCategory);
        this.props.addCategory(newCategory,this.props.history);
    }
    /* */
    render() {
      //Bat loi
      const { errors} =this.state;
        return (
            <div className="addProjectTask">
            <div className="container">
              <div className="row">
                <div className="col-md-8 m-auto">
                  <a href="/ProjectBoard.html" className="btn btn-light">
                    Back to Boarda
                  </a>
                  <h4 className="display-4 text-center">Thêm danh mục sản phẩm</h4>
                  <form onSubmit={this.onSubmit}>
                    <div className="form-group">
                        <label >Tên danh mục:</label>
                      <input type="text" className={classnames("form-control form-control-lg",{
                        "is-invalid":errors.nameCategory
                      })} name="nameCategory" 
                      placeholder="Nhập tên danh mục..." value={this.state.nameCategory} 
                      onChange={this.onChange}
                      />
                       {errors.nameCategory && (
                      <div className="invalid-feedback">{errors.nameCategory}</div>
                      )}
                    </div>
                   
                     
                    
                    <div className="form-group">
                    <label >Ghi chú:</label>
                      <textarea className="form-control form-control-lg" value={this.state.note}
                       placeholder="Ghi chú tại đây" name="note" 
                       onChange={this.onChange} />
                    </div>
                    <div className="form-group">
                      <select className="form-control form-control-lg" name="status" value={this.state.status}
                       onChange={this.onChange}>
                        <option value>Select Status</option>
                        <option value="TO_DO">TO DO</option>
                        <option value="IN_PROGRESS">IN PROGRESS</option>
                        <option value="DONE">DONE</option>
                      </select>
                    </div>
                    <input type="submit" className="btn btn-primary btn-block mt-4" />
                  </form>
                </div>
              </div>
            </div>
          </div>
        );
    }
}

AddCategory.propTypes={
  /* addCategory nằm bên CategoryAction.js*/
  addCategory:  PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
}

const mapStateToProps = state=>({
  errors:state.errors
})
/*
export default AddCategory;
2011-08-21 14:11:09
*/
//export default AddCategory;
export default connect(mapStateToProps,{  addCategory }) (AddCategory);