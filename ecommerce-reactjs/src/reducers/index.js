import { combineReducers } from "redux";
import errorsReducer from "./errorsReducer";
import CategoryListReducer from "./CategoryListReducer";
import ProductNewReducer from "./ProductNewReducer";

export default combineReducers({
  //
  errors: errorsReducer,
  project_Category: CategoryListReducer,
  product_new: ProductNewReducer
});