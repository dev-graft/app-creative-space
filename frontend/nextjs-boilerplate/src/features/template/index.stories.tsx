import TemplatePage from "./index";
import {ComponentMeta, ComponentStory} from '@storybook/react';

export default {
    title: "pages/TemplatePage",
    component: TemplatePage
} as ComponentMeta<typeof TemplatePage>

const Template:ComponentStory<typeof TemplatePage> = () => <TemplatePage/> ;

// TODO Theme [Light, Dark] 추가 후 템플릿 분리할 예정
export const ThemePageTemplate = Template.bind({});


ThemePageTemplate.args = {

};