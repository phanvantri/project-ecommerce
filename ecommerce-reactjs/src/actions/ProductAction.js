import axios from 'axios';
import { async } from 'q';
import {GET_ERRORS,GET_PRODUCT_NEW,GET_ALL_PRODUCT} from './types'

//load san phẩm mới
export const getProductNew = ()  =>async dispatch =>{

    const res=await axios.get("http://localhost:8080/api/getproductnew");
    dispatch({
        type:GET_PRODUCT_NEW,
        payload:res.data
    })
}
//page load tất các sản phẩm
export const getAllProduct =() => async dispatch =>{
    const res =await axios.get("http://localhost:8080/api/getallProduct?page=1");
    dispatch({
        type:GET_ALL_PRODUCT,
        payload:res.data
    })
}