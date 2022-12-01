import React from 'react'
import Head from "next/head";
import {Container, TextField, Unstable_Grid2 as Grid} from '@mui/material'

export default function TemplatePage() {
    return (
        <Container>
            <Head>
                <title>Theme</title>
            </Head>
            <main>
                <h1>THEME BOARD</h1>
                <h2>TextField</h2>
                <Grid container spacing={3}>
                    <Grid>
                        <TextField label={'standard-field'} variant="standard"/>
                    </Grid>
                    <Grid>
                        <TextField label={'filled-field'} variant="filled"/>
                    </Grid>
                    <Grid>
                        <TextField label={'outlined-field'} variant="outlined"/>
                    </Grid>
                </Grid>
            </main>
        </Container>
    );
}