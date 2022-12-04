import styled, {css, CSSProp} from "styled-components";
import {Components, Styles} from '@/types'
import {ButtonProps} from "../../@types/components";

const styleTable: Record<ButtonProps["styleType"], CSSProp<Styles.ThemeType>> = {
    solid: css`
    ${({theme}: ThemeStyle) => css`
      color: ${theme.Palette.primary};
      background: ${theme.Palette.secondary};
      &: hover {
      }
    `}    
  `,
    outline: css`
    ${({theme}: ThemeStyle) => css`
      color: ${theme.Palette.secondary};
      background: transparent;
      border: 1px solid ${theme.Palette.secondary};
      &: hover {
        color: ${theme.Palette.primary};
        background-color: ${theme.Palette.secondary};
        border: 1px solid transparent;
      }
    `}
  `
};

const sizeTable: Record<ButtonProps["size"], CSSProp<Styles.ThemeType>> = {
    small: css`
    padding: 1 1.25;
    width: 80px;
    height: 20px;
      ${({theme}: ThemeStyle) => css`
      font-size: ${theme.Font.Size.medium};
    `}    
  `,
    medium: css`
    padding: 1 1.25;
    width: 100px;
    height: 30px;
      ${({theme}: ThemeStyle) => css`
      font-size: ${theme.Font.Size.medium};
    `}    
  `,
    large: css`
    padding: 1 1.25;
    width: 120px;
    height: 40px;
      ${({theme}: ThemeStyle) => css`
      font-size: ${theme.Font.Size.medium};
    `}    
  `,
};

export const Container = styled.button`
  border: none;
  border-radius: 5px;
  white-space: nowrap;
  cursor: pointer;
  transition: 0.5s;
  
  ${({size, styleType}: Pick<Components.ButtonProps, "size" | "styleType" | "onClick">) => css`
    ${styleTable[styleType]}
    ${sizeTable[size]};
  `}
`;