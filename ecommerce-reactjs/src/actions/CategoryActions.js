import axios from 'axios';
import { async } from 'q';
import { GET_ERRORS, GET_PROJECT_CATEGORY } from './types';
 
export const addCategory=(category_add,history) => async dispatch =>{

    try{

        await axios.post("http://localhost:8080/api/addcategory",category_add);
        history.push("/");
        dispatch({
            type:GET_ERRORS,
            payload:{}
        })
    }
    catch(error){

        dispatch({
            type:GET_ERRORS,
            payload:error.response.data
        })
    }
    
}

export const getAllCategory = () => async dispatch =>{
    const res=await axios.get("http://localhost:8080/api/category");
    dispatch({
        type:GET_PROJECT_CATEGORY,
        payload:res.data
    })
}
