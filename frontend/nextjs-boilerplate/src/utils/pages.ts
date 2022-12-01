import createCache from '@emotion/cache'

const isBrowser = typeof document !== 'undefined';

export function createEmotionCache() {
    let insertionPoint: any = undefined;
    if (isBrowser) {
        const emotionInsertionPoint = document.querySelector('meta[name="emotion-insertion-point"]');
        insertionPoint = emotionInsertionPoint ?? undefined;
    }
    return createCache({key: 'mui-style', insertionPoint: insertionPoint});
}