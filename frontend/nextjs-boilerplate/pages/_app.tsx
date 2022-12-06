/*emotion + mui*/
import {ThemeProvider as MuiThemeProvider, createTheme} from '@mui/material/styles'
import CssBaseline from '@mui/material/CssBaseline';
import {CacheProvider} from '@emotion/react';
import {createEmotionCache} from "@/utils/pages";
import {EmotionAppProps} from "@/types/pages";

const clientSideEmotionCache = createEmotionCache();

/**https://velog.io/@uhui94/mui-%EC%82%AC%EC%9A%A9%EA%B8%B0**/
const theme = createTheme({
    palette: {
        primary: {
            main: "#161616"
        },
        secondary: {
            main: "#EEFD53"
        },
        error: {
            main: "#DA1E28"
        }
    },
    typography: {
        fontFamily: ["Noto Sans KR", "sans-serif", "-apple-system", "BlinkMacSystemFont", "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue"].join(",")
    }
});

export default function App({Component, pageProps, emotionCache = clientSideEmotionCache}: EmotionAppProps) {
    return (
        <CacheProvider value={emotionCache}>
            <MuiThemeProvider theme={theme}>
                <CssBaseline/>
                <Component {...pageProps} />
            </MuiThemeProvider>
        </CacheProvider>
    )
}
