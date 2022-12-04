/*styled-components*/
import {ThemeProvider as StyledComponentsThemeProvider} from 'styled-components'
import {GlobalStyle, Theme} from '@/styles'
/*emotion + mui*/
import {ThemeProvider as MuiThemeProvider} from '@mui/material/styles'
import CssBaseline from '@mui/material/CssBaseline';
import {CacheProvider} from '@emotion/react';
import {createEmotionCache} from "@/utils/pages";
import {EmotionAppProps} from "@/types/pages";

const clientSideEmotionCache = createEmotionCache();

export default function App({Component, pageProps, emotionCache = clientSideEmotionCache}: EmotionAppProps) {
    return (
        <CacheProvider value={emotionCache}>
            <MuiThemeProvider theme={Theme.LightMode}>
                <CssBaseline/>
                <StyledComponentsThemeProvider theme={Theme.LightMode}>
                    <GlobalStyle/>
                    <Component {...pageProps} />
                </StyledComponentsThemeProvider>
            </MuiThemeProvider>
        </CacheProvider>
    )
}
