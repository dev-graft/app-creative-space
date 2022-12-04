import {ComponentMeta, ComponentStory} from '@storybook/react';
import Button from "./index"

export default {
    title: "shard/Button",
    component: Button
} as ComponentMeta<typeof Button>

const Template: ComponentStory<typeof Button> = (args) => <Button {...args}/>;

export const ButtonTemplate = Template.bind({});

ButtonTemplate.args = {
    styleType: 'solid',
    size:  'medium',
    children: "Button",
};