import {ComponentMeta, ComponentStory} from '@storybook/react';
import Navigation from './index'

export default {
    title: "layout/Navigation",
    component: Navigation
} as ComponentMeta<typeof Navigation>

const Template: ComponentStory<typeof Navigation> = () => <Navigation variant="temporary"/>;

export const NavigationTemplate = Template.bind({});