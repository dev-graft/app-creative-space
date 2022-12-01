import {muiTheme} from 'storybook-addon-material-ui'
import {addDecorator} from "@storybook/react";
import {withThemesProvider} from "storybook-addon-styled-component-theme";
import {ThemeProvider} from "styled-components";
import {Theme} from '@/styles'

export const decorators = [
];
export const parameters = {
  actions: {argTypesRegex: "^on[A-Z].*"},
  controls: {
    matchers: {
      color: /(background|color)$/i,
      date: /Date$/,
    },
  },
};

const themes = () => {
  return [Theme.LightMode, Theme.DarkMode,   muiTheme()];
};
addDecorator(withThemesProvider(themes()), ThemeProvider);