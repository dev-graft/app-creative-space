import {useRoutes} from 'react-router-dom'
import * as React from "react";


const TestRoutes = {
    path: '/',
    element: <><p>Hello</p></>,
    children: [{path:'/', element:<><p>Hello</p></>}]
};

export default function Routes() {
    return useRoutes([TestRoutes])
}