import LoginFeature from "./index";
import {ComponentMeta, ComponentStory} from '@storybook/react';

export default {
    title: "features/Login",
    component: LoginFeature
} as ComponentMeta<typeof LoginFeature>

const Template:ComponentStory<typeof LoginFeature> = () => <LoginFeature/> ;

export const LoginFeatureTemplate = Template.bind(()=>{});


LoginFeatureTemplate.args = {

};