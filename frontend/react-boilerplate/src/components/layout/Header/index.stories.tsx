import {ComponentMeta, ComponentStory} from '@storybook/react';
import Header from './index'

export default {
    title: "layout/Header",
    component: Header
} as ComponentMeta<typeof Header>

const Template: ComponentStory<typeof Header> = (args) => <Header onDrawerToggle={()=>{console.log('TEST')}}/>;

export const HeaderTemplate = Template.bind({});