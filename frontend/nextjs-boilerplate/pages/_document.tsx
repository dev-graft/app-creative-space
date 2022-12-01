import Document, {DocumentContext} from "next/document";
import {ServerStyleSheet} from "styled-components";
import createEmotionServer from '@emotion/server/create-instance';
import {createEmotionCache} from '../src/utils/pages'

export default class MyDocument extends Document {
    static async getInitialProps(ctx: DocumentContext) {
        const originalRenderPage = ctx.renderPage;

        const sheet = new ServerStyleSheet();
        const cache = createEmotionCache();
        const {extractCriticalToChunks} = createEmotionServer(cache);

        try {
            ctx.renderPage = () =>
                originalRenderPage({
                    enhanceApp: (App:any) =>
                        (props) =>
                            sheet.collectStyles(<App emotionCache={cache} {...props} />),
                });
            const initialProps = await Document.getInitialProps(ctx);
            const emotionStyles = extractCriticalToChunks(initialProps.html);
            const emotionStyleTags = emotionStyles.styles.map((style) => (
                <style
                    data-emotion={`${style.key} ${style.ids.join(' ')}`}
                    key={style.key}
                    // eslint-disable-next-line react/no-danger
                    dangerouslySetInnerHTML={{__html: style.css}}
                />
            ));
            return {
                ...initialProps,
                emotionStyleTags,
                styles: (
                    <>
                        {initialProps.styles}
                        {sheet.getStyleElement()}
                    </>
                ),
            };
        } finally {
            sheet.seal();
        }
    }
}