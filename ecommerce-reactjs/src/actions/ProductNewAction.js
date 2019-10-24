import axios from 'axios';
import { async } from 'q';
import {GET_ERRORS,GET_PRODUCT_NEW} from './types'

export const getProductNew = ()  =>async dispatch =>{

    const res=await axios.get("http://localhost:8080/api/getproductnew");
    dispatch({
        type:GET_PRODUCT_NEW,
        payload:res.data
    })
}