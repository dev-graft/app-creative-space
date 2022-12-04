import {EmotionCache} from "@emotion/utils";
import {AppProps} from "next/app";

export type EmotionAppProps = AppProps&{
    emotionCache: EmotionCache
}