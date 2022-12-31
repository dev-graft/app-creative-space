import * as React from "react";
import type {RouteObject} from 'react-router-dom'

const MainRoutes:RouteObject = {
    path: '/',
    element: <><p>Hello</p></>,
    children: [{path:'/', element:<><p>Hello</p></>}]
};

export default MainRoutes;