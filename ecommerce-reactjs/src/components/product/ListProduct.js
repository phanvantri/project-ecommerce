import React, { Component } from 'react';
import ListProductNew from './ListProductNew';
import Discount from './Discount';

class ListProduct extends Component {

    render() {
        return (
            <div className="ads-grid py-sm-5 py-4">
                <div className="product-sec1 px-sm-4 px-3 py-sm-5  py-3 mb-4">     
                        <div className="row">
                            <Discount></Discount>
                                <div className="agileinfo-ads-display col-lg-9">
                                            <div className="wapper">
                                                <ListProductNew></ListProductNew>
                                                <ListProductNew></ListProductNew>
                                            </div>
                                </div>
                        </div>
                    </div>
            </div>
        );
    }
}

export default ListProduct;