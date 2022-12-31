import {ComponentMeta, ComponentStory} from '@storybook/react';
import Box from '@mui/material/Box';
import useState from 'storybook-addon-state';
import Navigation from '@/components/layout/Navigation'

export default {
    title: "layout/Navigation",
    component: Navigation
} as ComponentMeta<typeof Navigation>

const drawerWidth = 256;
const [mobileOpen, setMobileOpen] = useState('clicks', true);
const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
};
const Template: ComponentStory<typeof Navigation> = () =>
    <Box
        component="nav"
        sx={{ width: { sm: drawerWidth }, flexShrink: { sm: 0 } }}
    >
        {'' ? null : (
            <Navigation
                PaperProps={{ style: { width: drawerWidth } }}
                variant="temporary"
                open={mobileOpen}
                onClose={handleDrawerToggle}
            />
        )}
        <Navigation
            PaperProps={{ style: { width: drawerWidth } }}
            sx={{ display: { sm: 'block', xs: 'none' } }}
        />
    </Box>;

export const NavigationTemplate = Template.bind({});