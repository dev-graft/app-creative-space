import React from 'react'
import Head from "next/head";
import {Container, Button} from '@mui/material'

export default function LoginFeature() {
    return (
        <Container>
            <Head>
                <title>Clicker</title>
            </Head>
            <main>
                <h1>Clicker</h1>
                <Button/>
            </main>
        </Container>
    );
}