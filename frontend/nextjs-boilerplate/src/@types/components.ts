import {ButtonHTMLAttributes, ReactNode} from "react";

export type FunctionChildren = (...args: any) => ReactNode;
export type StrictPropsWithChildren<P = unknown> = P & {
    children: ReactNode;
}
export type PropsWithFunctionChildren<P = unknown,
    F extends FunctionChildren = (...args: any) => ReactNode> = P & {
    children: F;
};

/** Size[s,m,l] 을 사용하는 곳은 Components와 Styles의 Font다. 과연 여기에 사용되는 심볼을 묶는게 의미 있을까 **/

/** 스타일을 정의하는 과정에서 small을 잘못 입력하는 경우 에러를 알리려면 어떻게 해야할까 내가 정의한 심볼에만 맞게 입력을 강제하는 방법은? **/

export const SizeKind = {
    small: 'small',
    medium: 'medium',
    large: 'large'
} as const;

export type StyleKind = {
    solid: "solid",
    outline: "outline"
};

export type ButtonProps = StrictPropsWithChildren<ButtonHTMLAttributes<HTMLButtonElement>> & {
    size: typeof SizeKind.small | typeof SizeKind.medium | typeof SizeKind.large,
    styleType: StyleKind['solid'] | StyleKind['outline']
};

// border width
// medium | thick | thin | length | initial | inherit

// border style
// dashed | dotted | double | groove | hidden | inset | none | outset | ridge | solid | initial | inherit