const path = require("path");

module.exports = {
  "stories": [
    "../src/**/*.stories.mdx",
    "../src/**/*.stories.@(js|jsx|ts|tsx)",
    "../src/components/**/*.stories.@(js|jsx|ts|tsx)",
    // "../src/features/**/*.stories.@(js|jsx|ts|tsx)",
    // "../pages/**/*.stories.@(js|jsx|ts|tsx)",
  ],
  "addons": [
    "@storybook/addon-links",
    "@storybook/addon-essentials",
    "@storybook/addon-interactions",
    "@storybook/preset-create-react-app",
    "storybook-addon-material-ui",
    "storybook-addon-state"
  ],
  "framework": "@storybook/react",
  "core": {
    "builder": "@storybook/builder-webpack5"
  },
  webpackFinal: async (config) => {
    config.resolve.alias = {
      ...config.resolve.alias,
      "@/styles": path.resolve(__dirname, "../src/@styles"),
      "@/types": path.resolve(__dirname, "../src/@types"),
      "@/components": path.resolve(__dirname, "../src/components"),
      "@/features": path.resolve(__dirname, "../src/features"),
      "@/pages": path.resolve(__dirname, "../pages"),
      "@/utils": path.resolve(__dirname, "../src/utils"),
      "@public": path.resolve(__dirname, "../public/"),
    };

    return config;
  },
};