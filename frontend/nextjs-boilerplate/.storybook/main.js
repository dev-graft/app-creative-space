const path = require("path");

module.exports = {
    "stories": [
        // "../stories/**/*.stories.mdx",
        // "../stories/**/*.stories.@(js|jsx|ts|tsx)",
        "../src/components/**/*.stories.@(js|jsx|ts|tsx)",
        "../src/features/**/*.stories.@(js|jsx|ts|tsx)",
        "../pages/**/*.stories.@(js|jsx|ts|tsx)",
    ],
    "addons": [
        "@storybook/addon-links",
        "@storybook/addon-essentials",
        "@storybook/addon-interactions",
        "storybook-addon-styled-component-theme/dist/preset",
        'storybook-addon-material-ui',
    ],
    "framework": "@storybook/react",
    "core": {
        "builder": "@storybook/builder-webpack5"
    },
    staticDirs: ["./public"],
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