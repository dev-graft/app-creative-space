import React from 'react'
import Head from "next/head";
import {Button, Container, TextField, Unstable_Grid2 as Grid} from '@mui/material'

export default function LoginFeature() {
    return (
        <Container>
            <Head>
                <title>Theme</title>
            </Head>
            <main>
                <h1>THEME BOARD</h1>
                <h2>Syuck Syuck</h2>
                <Grid container rowSpacing={1} columnSpacing={{ xs: 1, sm: 2, md: 3 }}>
                    <Grid xs={12}>
                        <TextField label={'아이디'} variant="filled"/>

                    </Grid>
                    <Grid xs={12}>
                        <TextField label={'비밀번호'} variant="filled" type="password"/>
                    </Grid>
                </Grid>
                <Button placeholder={"LOGIN"}/>
            </main>
        </Container>
    );
}