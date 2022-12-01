import {Styles} from "@/types";

const _FontSize = {
    "34px": "2.125rem",
    "20px": "1.25rem",
    "18px": "1.125rem",
    "14px": "0.875rem",
    "12px": "0.75rem",
    "10px": "0.625rem",
} as const;

const basicFontSize = {
    small: _FontSize["10px"],
    medium: _FontSize["14px"],
    large: _FontSize["18px"]
};

const RobotoFont: Styles.FontType = {
    Family : {
        primary: 'Roboto',
        secondary: 'Twayair'
    },
    Size: basicFontSize
};

export const Fonts = {
    Roboto: RobotoFont
};

// TODO 우아한 쪽에서 제공하는 폰트도 넣어볼 예정