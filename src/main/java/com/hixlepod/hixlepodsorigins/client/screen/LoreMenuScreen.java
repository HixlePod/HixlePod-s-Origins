package com.hixlepod.hixlepodsorigins.client.screen;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class LoreMenuScreen extends Screen {

    private static String Title = "";

    //Lore Menu
    private static String Quote = "";
    private static int QuotePosition = 0;

    private static String Description = "";
    private MultiLineLabel message = MultiLineLabel.EMPTY;

    private static int TheJournalPage = 1;

    //World laws

    private static int WorldLawsPage = 1;

    private enum Page {
        WORLD_LAWS,
        THE_JOURNAL,
        PLACEHOLDER
    }

    private static Page CurrentPage = Page.WORLD_LAWS;

    public LoreMenuScreen(String title) {
        super(Component.literal(title).withStyle(ChatFormatting.GOLD));
    }

    @Override
    protected void init() {
        super.init();
        //width, height, size x, size y

        //World Laws
        addRenderableWidget(new Button(5, 30, 70, 20, Component.literal("World Laws"), LoreMenuScreen::WorldLawsButton));

        //Messangers Journal
        addRenderableWidget(new Button(5, 55, 70, 20, Component.literal("The Journal"), LoreMenuScreen::JournalButton));

        //Soon to be added.
        addRenderableWidget(new Button(5, 80, 70, 20, Component.literal("Placeholder"), LoreMenuScreen::PlaceholderButton));

        //Page buttons
        addRenderableWidget(new Button(((this.width / 2) - 30) - 10, this.height - 25, 20, 20, Component.literal("<-"), LoreMenuScreen::LeftButton));
        addRenderableWidget(new Button(((this.width / 2) + 30) - 10, this.height - 25, 20, 20, Component.literal("->"), LoreMenuScreen::RightButton));

        this.message = MultiLineLabel.create(this.font, Component.literal(Description), (this.width / 2) - 15);
    }

    private static final ResourceLocation WHAT_EVER_THIS_IS = new ResourceLocation(HixlePodsOrigins.MODID, "textures/lorebook/characters/whatever_this_is.png");
    private static final ResourceLocation WHIRL_SKETCH = new ResourceLocation(HixlePodsOrigins.MODID, "textures/lorebook/characters/whirl_sketch.png");
    private static final ResourceLocation ELENA_SKETCH = new ResourceLocation(HixlePodsOrigins.MODID, "textures/lorebook/characters/elena_sketch.png");

    private static ResourceLocation CURRENT_RESOURCE = WHAT_EVER_THIS_IS;


    @Override
    public void render(PoseStack poseStack, int p_96563_, int p_96564_, float p_96565_) {

        //Tints the background
        this.renderBackground(poseStack);

        //Screen title
        drawString(poseStack, this.font, Component.literal(Title), (this.width / 2) - 23, 15, 13158600);

        drawString(poseStack, this.font, Component.literal(Quote), QuotePosition, 30, 6451573);

        int i = this.width / 2 - this.message.getWidth() / 2;
        this.message.renderLeftAligned(poseStack, i, 70, 9 * 2, 16777215);

        int scale = 77;

        int posY = 41;
        int posX = (this.width / 2) - (57 + scale + 25);

        //NEW ONE
        if (CurrentPage == Page.THE_JOURNAL) {
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f); // I set the opacity of the draw here

            RenderSystem.setShaderTexture(0, CURRENT_RESOURCE);

            //this.blit(poseStack, posX, posY, 0, 0, scaledWidth, scaledHeight);
            this.blit(poseStack, posX, posY, 0, 0.0f, 0.0f, 57 + scale, 110 + scale, 57 + scale, 110 + scale);

            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.disableBlend();
        }

        super.render(poseStack, p_96563_, p_96564_, p_96565_);
    }

    private static void WorldLawsButton(Button button) {
        CurrentPage = Page.WORLD_LAWS;

        updatePageContents();
    }

    private static void JournalButton(Button button) {
        CurrentPage = Page.THE_JOURNAL;

        updatePageContents();
    }

    private static void PlaceholderButton(Button button) {
        CurrentPage = Page.PLACEHOLDER;

        updatePageContents();
    }


    private static void LeftButton(Button button) {
        if (CurrentPage == Page.THE_JOURNAL) {

            if (TheJournalPage > 1) {
                TheJournalPage -= 1;
            }

            updatePageContents();
        }
    }

    private static void RightButton(Button button) {
        if (CurrentPage == Page.THE_JOURNAL) {

            if (TheJournalPage >= 1) {
                TheJournalPage += 1;
            }

            updatePageContents();
        }
    }

    private static void updatePageContents() {
        if (CurrentPage == Page.THE_JOURNAL) {
            if (TheJournalPage == 1) {
                Title = "Journal - Whirl";
                Quote = ChatFormatting.ITALIC + "\"Predictably unpredictable… what a free yet trapped spirit.\"";
                QuotePosition = 115;
                CURRENT_RESOURCE = WHIRL_SKETCH;
                Description = "This inhabitant, ‘Whirl’, appears to be the leader of " +
                        "‘The Wreckers’ faction. On his own he’s of little threat, and " +
                        "yet somehow has managed to coerce the cooperation of two rather " +
                        "dangerous individuals. For all that she’s petty, childish, and " +
                        "impulsive, she’s quick to use her powers in many given circumstances. " +
                        "A power that aids allies and detriments enemies. He holds little " +
                        "interest in getting his hands dirty, perhaps aware of his own weaknesses " +
                        "even if he may not acknowledge them. Perhaps amongst the few in this " +
                        "world without a secret to tell, Whirl simply enjoys the present and " +
                        "cares little for the past and future. Though, admittedly, the lack of " +
                        "secrets may have to do with their inability to internalise their thoughts.";

            } else if (TheJournalPage == 2) {
                Title = "Journal - Whisper";
                Quote = ChatFormatting.ITALIC + "\"Shh, don’t make a sound. He can hear you.\"";
                QuotePosition = 155;
                CURRENT_RESOURCE = WHAT_EVER_THIS_IS;

            } else if (TheJournalPage == 3) {
                Title = "Journal - Elena";
                Quote = "";
                QuotePosition = 140;
                CURRENT_RESOURCE = ELENA_SKETCH;

            } else {
                CURRENT_RESOURCE = WHAT_EVER_THIS_IS;
            }

        } else if (CurrentPage == Page.WORLD_LAWS) {
            Title = "World Laws";
            Quote = "";
            Description = "";

        } else if (CurrentPage == Page.PLACEHOLDER) {
            Title = "Placeholder";
            Quote = "";
            Description = "";
        }
    }
}
