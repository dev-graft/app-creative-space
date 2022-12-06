import React from 'react'
import Head from "next/head";
import {Container} from '@mui/material'
import { Button } from '@/components';

export default function LoginFeature() {
    return (
        <Container>
            <Head>
                <title>Clicker</title>
            </Head>
            <main>
                <h1>Clicker</h1>
                <Button size={'medium'} value={'Click!!'}/>
            </main>
        </Container>
    );
}