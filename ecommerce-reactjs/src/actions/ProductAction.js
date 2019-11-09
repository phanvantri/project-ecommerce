import axios from 'axios';
import { async } from 'q';
import {GET_ERRORS,GET_PRODUCT_NEW,GET_ALL_PRODUCT, GET_PRODUCT_DETAIL} from './types'

//load san phẩm mới
export const getProductNew = ()  =>async dispatch =>{

    const res=await axios.get("http://localhost:8080/api/getproductnew");
    dispatch({
        type:GET_PRODUCT_NEW,
        payload:res.data
    })
}
//page load tất các sản phẩm
export const getAllProduct =(page) => async dispatch =>{
    
    const res =await axios.get("http://localhost:8080/api/getallProduct?page="+page+"&size=5");
    dispatch({
        type:GET_ALL_PRODUCT,
        payload:res.data
    })
}
//get product detail
export const getproductDetail=(id) => async dispatch =>{
  
    const res= await axios.get("http://localhost:8080/api/productdetail?id="+id);

    dispatch({
        type:GET_PRODUCT_DETAIL,
        payload:res.data
    })
}