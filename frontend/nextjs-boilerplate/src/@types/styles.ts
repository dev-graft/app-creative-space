export type PaletteType = {
    /*컬러 요소*/
    primary: string,
    primaryLight: string,
    primaryDark: string,
    secondary: string,
    secondaryLight: string,
    secondaryDark: string,
    /*시맨틱 요소*/
    accent: string,
    text: string,
    textPrimary: string,
    textSecondary: string,
    textDisabled: string,
    background: string,
    backgroundElevated: string,
    divider: string,
};

export type FontType = {
    Family : {
        primary: string,
        secondary: string
    },
    Size: {
        small: string,
        medium: string,
        large: string
    }
}

declare global {
    type ThemeStyle = {
        theme: ThemeType
    }
}

export type ThemeType = {
    name: string,
    Palette: PaletteType,
    Font: FontType
};