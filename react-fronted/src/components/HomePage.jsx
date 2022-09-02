import React from 'react'
// import { useNavigate } from 'react-router-dom';
import Login from './Login';

export default function HomePage() {

    const titleStyle = {
        "display" :"flex",
        "height": "20vh",
        "justifyContent": "center",
        "alignItems": "center",
        "fontSize": "4em",
        "textShadow": "2px 2px #ff0000",
        "fontWeight": "bolder"
    };

    const wrapper = {
        "textAlign":"center"
    }

    const divStyle = {
        "display" :"flex",
        "marginBottom": "30px",
    }

    // let navigate = useNavigate();
    // const inside = function(){
    //     navigate("/customers");
    // }

    return (
    <div>
        <h1 style={titleStyle}>Customer Detail System</h1>
        <div style={divStyle}>
            <Login/>
        </div>
        {/* <div style={wrapper}>
            <button className='btn btn-primary btn-lg mb-5' onClick={inside}>Enter</button>
        </div> */}
    </div>
    )
}
