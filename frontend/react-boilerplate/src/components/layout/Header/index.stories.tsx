import {ComponentMeta, ComponentStory} from '@storybook/react';
import Header from '@/components//layout/Header'

export default {
    title: "layout/Header",
    component: Header
} as ComponentMeta<typeof Header>

const Template: ComponentStory<typeof Header> = () => <Header onDrawerToggle={()=>{console.log("hello")}}/>;

export const HeaderTemplate = Template.bind({});