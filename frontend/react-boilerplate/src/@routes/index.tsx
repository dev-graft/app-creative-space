import {useRoutes} from 'react-router-dom'
import MainRoutes from "@/routes/MainRoutes";
import * as React from "react";

export default function Routes() {
    return useRoutes([MainRoutes])
}