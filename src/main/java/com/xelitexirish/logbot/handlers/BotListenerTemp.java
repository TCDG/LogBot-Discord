/**
 * This class was created by <KingDGrizzle>. It's distributed as
 * part of the LogBot Mod. Get the Source Code in github:
 * https://github.com/KingDGrizzle/LogBot
 * 
 * File Created @ [4 dec. 2016, 14:07:57 (GMT)]
 */
package com.xelitexirish.logbot.handlers;

import net.dv8tion.jda.client.events.call.CallCreateEvent;
import net.dv8tion.jda.client.events.call.CallDeleteEvent;
import net.dv8tion.jda.client.events.call.GenericCallEvent;
import net.dv8tion.jda.client.events.call.update.CallUpdateRegionEvent;
import net.dv8tion.jda.client.events.call.update.CallUpdateRingingUsersEvent;
import net.dv8tion.jda.client.events.call.update.GenericCallUpdateEvent;
import net.dv8tion.jda.client.events.call.voice.CallVoiceJoinEvent;
import net.dv8tion.jda.client.events.call.voice.CallVoiceLeaveEvent;
import net.dv8tion.jda.client.events.call.voice.CallVoiceSelfDeafenEvent;
import net.dv8tion.jda.client.events.call.voice.CallVoiceSelfMuteEvent;
import net.dv8tion.jda.client.events.call.voice.GenericCallVoiceEvent;
import net.dv8tion.jda.client.events.group.GenericGroupEvent;
import net.dv8tion.jda.client.events.group.GroupJoinEvent;
import net.dv8tion.jda.client.events.group.GroupLeaveEvent;
import net.dv8tion.jda.client.events.group.GroupUserJoinEvent;
import net.dv8tion.jda.client.events.group.GroupUserLeaveEvent;
import net.dv8tion.jda.client.events.group.update.GenericGroupUpdateEvent;
import net.dv8tion.jda.client.events.group.update.GroupUpdateIconEvent;
import net.dv8tion.jda.client.events.group.update.GroupUpdateNameEvent;
import net.dv8tion.jda.client.events.group.update.GroupUpdateOwnerEvent;
import net.dv8tion.jda.client.events.message.group.GenericGroupMessageEvent;
import net.dv8tion.jda.client.events.message.group.GroupMessageDeleteEvent;
import net.dv8tion.jda.client.events.message.group.GroupMessageEmbedEvent;
import net.dv8tion.jda.client.events.message.group.GroupMessageReceivedEvent;
import net.dv8tion.jda.client.events.message.group.GroupMessageUpdateEvent;
import net.dv8tion.jda.client.events.relationship.FriendAddedEvent;
import net.dv8tion.jda.client.events.relationship.FriendRemovedEvent;
import net.dv8tion.jda.client.events.relationship.FriendRequestCanceledEvent;
import net.dv8tion.jda.client.events.relationship.FriendRequestIgnoredEvent;
import net.dv8tion.jda.client.events.relationship.FriendRequestReceivedEvent;
import net.dv8tion.jda.client.events.relationship.FriendRequestSentEvent;
import net.dv8tion.jda.client.events.relationship.GenericRelationshipAddEvent;
import net.dv8tion.jda.client.events.relationship.GenericRelationshipEvent;
import net.dv8tion.jda.client.events.relationship.GenericRelationshipRemoveEvent;
import net.dv8tion.jda.client.events.relationship.UserBlockedEvent;
import net.dv8tion.jda.client.events.relationship.UserUnblockedEvent;
import net.dv8tion.jda.core.events.DisconnectEvent;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.ReconnectedEvent;
import net.dv8tion.jda.core.events.ResumedEvent;
import net.dv8tion.jda.core.events.ShutdownEvent;
import net.dv8tion.jda.core.events.StatusChangeEvent;
import net.dv8tion.jda.core.events.channel.priv.PrivateChannelCreateEvent;
import net.dv8tion.jda.core.events.channel.priv.PrivateChannelDeleteEvent;
import net.dv8tion.jda.core.events.channel.text.GenericTextChannelEvent;
import net.dv8tion.jda.core.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.core.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.core.events.channel.text.update.GenericTextChannelUpdateEvent;
import net.dv8tion.jda.core.events.channel.text.update.TextChannelUpdateNameEvent;
import net.dv8tion.jda.core.events.channel.text.update.TextChannelUpdatePermissionsEvent;
import net.dv8tion.jda.core.events.channel.text.update.TextChannelUpdatePositionEvent;
import net.dv8tion.jda.core.events.channel.text.update.TextChannelUpdateTopicEvent;
import net.dv8tion.jda.core.events.channel.voice.GenericVoiceChannelEvent;
import net.dv8tion.jda.core.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.core.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.core.events.channel.voice.update.GenericVoiceChannelUpdateEvent;
import net.dv8tion.jda.core.events.channel.voice.update.VoiceChannelUpdateBitrateEvent;
import net.dv8tion.jda.core.events.channel.voice.update.VoiceChannelUpdateNameEvent;
import net.dv8tion.jda.core.events.channel.voice.update.VoiceChannelUpdatePermissionsEvent;
import net.dv8tion.jda.core.events.channel.voice.update.VoiceChannelUpdatePositionEvent;
import net.dv8tion.jda.core.events.channel.voice.update.VoiceChannelUpdateUserLimitEvent;
import net.dv8tion.jda.core.events.guild.GenericGuildEvent;
import net.dv8tion.jda.core.events.guild.GuildAvailableEvent;
import net.dv8tion.jda.core.events.guild.GuildBanEvent;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.core.events.guild.GuildUnavailableEvent;
import net.dv8tion.jda.core.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.core.events.guild.UnavailableGuildJoinedEvent;
import net.dv8tion.jda.core.events.guild.member.GenericGuildMemberEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberNickChangeEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.core.events.guild.update.GenericGuildUpdateEvent;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateAfkChannelEvent;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateAfkTimeoutEvent;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateIconEvent;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateMFALevelEvent;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateNotificationLevelEvent;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateOwnerEvent;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateRegionEvent;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateSplashEvent;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateVerificationLevelEvent;
import net.dv8tion.jda.core.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceDeafenEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceGuildDeafenEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceGuildMuteEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMuteEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceSelfDeafenEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceSelfMuteEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceSuppressEvent;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;
import net.dv8tion.jda.core.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.core.events.message.MessageDeleteEvent;
import net.dv8tion.jda.core.events.message.MessageEmbedEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.MessageUpdateEvent;
import net.dv8tion.jda.core.events.message.guild.GenericGuildMessageEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageEmbedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.core.events.message.priv.GenericPrivateMessageEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageDeleteEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageEmbedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageUpdateEvent;
import net.dv8tion.jda.core.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveAllEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.core.events.role.GenericRoleEvent;
import net.dv8tion.jda.core.events.role.RoleCreateEvent;
import net.dv8tion.jda.core.events.role.RoleDeleteEvent;
import net.dv8tion.jda.core.events.role.update.GenericRoleUpdateEvent;
import net.dv8tion.jda.core.events.role.update.RoleUpdateColorEvent;
import net.dv8tion.jda.core.events.role.update.RoleUpdateHoistedEvent;
import net.dv8tion.jda.core.events.role.update.RoleUpdateMentionableEvent;
import net.dv8tion.jda.core.events.role.update.RoleUpdateNameEvent;
import net.dv8tion.jda.core.events.role.update.RoleUpdatePermissionsEvent;
import net.dv8tion.jda.core.events.role.update.RoleUpdatePositionEvent;
import net.dv8tion.jda.core.events.self.GenericSelfUpdateEvent;
import net.dv8tion.jda.core.events.self.SelfUpdateAvatarEvent;
import net.dv8tion.jda.core.events.self.SelfUpdateEmailEvent;
import net.dv8tion.jda.core.events.self.SelfUpdateMFAEvent;
import net.dv8tion.jda.core.events.self.SelfUpdateNameEvent;
import net.dv8tion.jda.core.events.self.SelfUpdateVerifiedEvent;
import net.dv8tion.jda.core.events.user.GenericUserEvent;
import net.dv8tion.jda.core.events.user.UserAvatarUpdateEvent;
import net.dv8tion.jda.core.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.core.events.user.UserNameUpdateEvent;
import net.dv8tion.jda.core.events.user.UserTypingEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListenerTemp extends ListenerAdapter {
	
	@Override
	public void onCallCreate(CallCreateEvent event) {
		super.onCallCreate(event);
	}
	
	@Override
	public void onCallDelete(CallDeleteEvent event) {
		super.onCallDelete(event);
	}
	
	@Override
	public void onCallUpdateRegion(CallUpdateRegionEvent event) {
		super.onCallUpdateRegion(event);
	}
	
	@Override
	public void onCallUpdateRingingUsers(CallUpdateRingingUsersEvent event) {
		super.onCallUpdateRingingUsers(event);
	}

	@Override
	public void onCallVoiceJoin(CallVoiceJoinEvent event) {
		super.onCallVoiceJoin(event);
	}
	
	@Override
	public void onCallVoiceLeave(CallVoiceLeaveEvent event) {
		super.onCallVoiceLeave(event);
	}
	
	@Override
	public void onCallVoiceSelfDeafen(CallVoiceSelfDeafenEvent event) {
		super.onCallVoiceSelfDeafen(event);
	}
	
	@Override
	public void onCallVoiceSelfMute(CallVoiceSelfMuteEvent event) {
		super.onCallVoiceSelfMute(event);
	}
	
	@Override
	public void onDisconnect(DisconnectEvent event) {
		super.onDisconnect(event);
	}
	
	@Override
	public void onFriendAdded(FriendAddedEvent event) {
		super.onFriendAdded(event);
	}
	
	@Override
	public void onFriendRemoved(FriendRemovedEvent event) {
		super.onFriendRemoved(event);
	}
	
	@Override
	public void onFriendRequestCanceled(FriendRequestCanceledEvent event) {
		super.onFriendRequestCanceled(event);
	}
	
	@Override
	public void onFriendRequestIgnored(FriendRequestIgnoredEvent event) {
		super.onFriendRequestIgnored(event);
	}
	
	@Override
	public void onFriendRequestReceived(FriendRequestReceivedEvent event) {
		super.onFriendRequestReceived(event);
	}
	@Override
	public void onFriendRequestSent(FriendRequestSentEvent event) {
		super.onFriendRequestSent(event);
	}
	@Override
	public void onGenericCall(GenericCallEvent event) {
		super.onGenericCall(event);
	}@Override
	public void onGenericCallUpdate(GenericCallUpdateEvent event) {
		super.onGenericCallUpdate(event);
	}
	@Override
	public void onGenericCallVoice(GenericCallVoiceEvent event) {
		super.onGenericCallVoice(event);
	}
	@Override
	public void onGenericEvent(Event event) {
		super.onGenericEvent(event);
	}
	@Override
	public void onGenericGroup(GenericGroupEvent event) {
		super.onGenericGroup(event);
	}
	@Override
	public void onGenericGroupMessage(GenericGroupMessageEvent event) {
		super.onGenericGroupMessage(event);
	}
	@Override
	public void onGenericGroupUpdate(GenericGroupUpdateEvent event) {
		super.onGenericGroupUpdate(event);
	}
	@Override
	public void onGenericGuild(GenericGuildEvent event) {
		super.onGenericGuild(event);
	}
	@Override
	public void onGenericGuildMember(GenericGuildMemberEvent event) {
		super.onGenericGuildMember(event);
	}
	@Override
	public void onGenericGuildMessage(GenericGuildMessageEvent event) {
		super.onGenericGuildMessage(event);
	}
	@Override
	public void onGenericGuildUpdate(GenericGuildUpdateEvent event) {
		super.onGenericGuildUpdate(event);
	}
	@Override
	public void onGenericGuildVoice(GenericGuildVoiceEvent event) {
		super.onGenericGuildVoice(event);
	}
	@Override
	public void onGenericMessage(GenericMessageEvent event) {
		super.onGenericMessage(event);
	}
	@Override
	public void onGenericMessageReaction(GenericMessageReactionEvent event) {
		super.onGenericMessageReaction(event);
	}
	@Override
	public void onGenericPrivateMessage(GenericPrivateMessageEvent event) {
		super.onGenericPrivateMessage(event);
	}
	@Override
	public void onGenericRelationship(GenericRelationshipEvent event) {
		super.onGenericRelationship(event);
	}
	@Override
	public void onGenericRelationshipAdd(GenericRelationshipAddEvent event) {
		super.onGenericRelationshipAdd(event);
	}
	@Override
	public void onGenericRelationshipRemove(GenericRelationshipRemoveEvent event) {
		super.onGenericRelationshipRemove(event);
	}
	@Override
	public void onGenericRole(GenericRoleEvent event) {
		super.onGenericRole(event);
	}
	@Override
	public void onGenericRoleUpdate(GenericRoleUpdateEvent event) {
		super.onGenericRoleUpdate(event);
	}
	@Override
	public void onGenericSelfUpdate(GenericSelfUpdateEvent event) {
		super.onGenericSelfUpdate(event);
	}
	@Override
	public void onGenericTextChannel(GenericTextChannelEvent event) {
		super.onGenericTextChannel(event);
	}
	@Override
	public void onGenericTextChannelUpdate(GenericTextChannelUpdateEvent event) {
		super.onGenericTextChannelUpdate(event);
	}
	@Override
	public void onGenericUser(GenericUserEvent event) {
		super.onGenericUser(event);
	}
	@Override
	public void onGenericVoiceChannel(GenericVoiceChannelEvent event) {
		super.onGenericVoiceChannel(event);
	}
	@Override
	public void onGenericVoiceChannelUpdate(GenericVoiceChannelUpdateEvent event) {
		super.onGenericVoiceChannelUpdate(event);
	}
	@Override
	public void onGroupJoin(GroupJoinEvent event) {
		super.onGroupJoin(event);
	}
	@Override
	public void onGroupLeave(GroupLeaveEvent event) {
		super.onGroupLeave(event);
	}
	@Override
	public void onGroupMessageDelete(GroupMessageDeleteEvent event) {
		super.onGroupMessageDelete(event);
	}
	@Override
	public void onGroupMessageEmbed(GroupMessageEmbedEvent event) {
		super.onGroupMessageEmbed(event);
	}
	@Override
	public void onGroupMessageReceived(GroupMessageReceivedEvent event) {
		super.onGroupMessageReceived(event);
	}
	@Override
	public void onGroupMessageUpdate(GroupMessageUpdateEvent event) {
		super.onGroupMessageUpdate(event);
	}
	@Override
	public void onGroupUpdateIcon(GroupUpdateIconEvent event) {
		super.onGroupUpdateIcon(event);
	}
	@Override
	public void onGroupUpdateName(GroupUpdateNameEvent event) {
		super.onGroupUpdateName(event);
	}
	@Override
	public void onGroupUpdateOwner(GroupUpdateOwnerEvent event) {
		super.onGroupUpdateOwner(event);
	}
	@Override
	public void onGroupUserJoin(GroupUserJoinEvent event) {
		super.onGroupUserJoin(event);
	}
	@Override
	public void onGroupUserLeave(GroupUserLeaveEvent event) {
		super.onGroupUserLeave(event);
	}
	@Override
	public void onGuildAvailable(GuildAvailableEvent event) {
		super.onGuildAvailable(event);
	}
	@Override
	public void onGuildBan(GuildBanEvent event) {
		super.onGuildBan(event);
	}
	@Override
	public void onGuildJoin(GuildJoinEvent event) {
		super.onGuildJoin(event);
	}
	@Override
	public void onGuildLeave(GuildLeaveEvent event) {
		super.onGuildLeave(event);
	}
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		super.onGuildMemberJoin(event);
	}
	@Override
	public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
		super.onGuildMemberLeave(event);
	}
	@Override
	public void onGuildMemberNickChange(GuildMemberNickChangeEvent event) {
		super.onGuildMemberNickChange(event);
	}
	@Override
	public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
		super.onGuildMemberRoleAdd(event);
	}
	@Override
	public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
		super.onGuildMemberRoleRemove(event);
	}
	@Override
	public void onGuildMessageDelete(GuildMessageDeleteEvent event) {
		super.onGuildMessageDelete(event);
	}
	@Override
	public void onGuildMessageEmbed(GuildMessageEmbedEvent event) {
		super.onGuildMessageEmbed(event);
	}
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		super.onGuildMessageReceived(event);
	}
	@Override
	public void onGuildMessageUpdate(GuildMessageUpdateEvent event) {
		super.onGuildMessageUpdate(event);
	}
	@Override
	public void onGuildUnavailable(GuildUnavailableEvent event) {
		super.onGuildUnavailable(event);
	}
	@Override
	public void onGuildUnban(GuildUnbanEvent event) {
		super.onGuildUnban(event);
	}
	@Override
	public void onGuildUpdateAfkChannel(GuildUpdateAfkChannelEvent event) {
		super.onGuildUpdateAfkChannel(event);
	}
	@Override
	public void onGuildUpdateAfkTimeout(GuildUpdateAfkTimeoutEvent event) {
		super.onGuildUpdateAfkTimeout(event);
	}
	@Override
	public void onGuildUpdateIcon(GuildUpdateIconEvent event) {
		super.onGuildUpdateIcon(event);
	}
	@Override
	public void onGuildUpdateMFALevel(GuildUpdateMFALevelEvent event) {
		super.onGuildUpdateMFALevel(event);
	}
	@Override
	public void onGuildUpdateName(GuildUpdateNameEvent event) {
		super.onGuildUpdateName(event);
	}
	
	@Override
	public void onGuildUpdateNotificationLevel(GuildUpdateNotificationLevelEvent event) {
		super.onGuildUpdateNotificationLevel(event);
	}
	@Override
	public void onGuildUpdateOwner(GuildUpdateOwnerEvent event) {
		super.onGuildUpdateOwner(event);
	}
	@Override
	public void onGuildUpdateRegion(GuildUpdateRegionEvent event) {
		super.onGuildUpdateRegion(event);
	}
	@Override
	public void onGuildUpdateSplash(GuildUpdateSplashEvent event) {
		super.onGuildUpdateSplash(event);
	}
	@Override
	public void onGuildUpdateVerificationLevel(GuildUpdateVerificationLevelEvent event) {
		super.onGuildUpdateVerificationLevel(event);
	}
	@Override
	public void onGuildVoiceDeafen(GuildVoiceDeafenEvent event) {
		super.onGuildVoiceDeafen(event);
	}
	@Override
	public void onGuildVoiceGuildDeafen(GuildVoiceGuildDeafenEvent event) {
		super.onGuildVoiceGuildDeafen(event);
	}
	@Override
	public void onGuildVoiceGuildMute(GuildVoiceGuildMuteEvent event) {
		super.onGuildVoiceGuildMute(event);
	}
	@Override
	public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
		super.onGuildVoiceJoin(event);
	}
	@Override
	public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
		super.onGuildVoiceLeave(event);
	}
	@Override
	public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
		super.onGuildVoiceMove(event);
	}
	@Override
	public void onGuildVoiceMute(GuildVoiceMuteEvent event) {
		super.onGuildVoiceMute(event);
	}
	@Override
	public void onGuildVoiceSelfDeafen(GuildVoiceSelfDeafenEvent event) {
		super.onGuildVoiceSelfDeafen(event);
	}
	@Override
	public void onGuildVoiceSelfMute(GuildVoiceSelfMuteEvent event) {
		super.onGuildVoiceSelfMute(event);
	}
	@Override
	public void onGuildVoiceSuppress(GuildVoiceSuppressEvent event) {
		super.onGuildVoiceSuppress(event);
	}
	@Override
	public void onMessageBulkDelete(MessageBulkDeleteEvent event) {
		super.onMessageBulkDelete(event);
	}
	@Override
	public void onMessageDelete(MessageDeleteEvent event) {
		super.onMessageDelete(event);
	}
	@Override
	public void onMessageEmbed(MessageEmbedEvent event) {
		super.onMessageEmbed(event);
	}
	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {
		super.onMessageReactionAdd(event);
	}
	@Override
	public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
		super.onMessageReactionRemove(event);
	}
	@Override
	public void onMessageReactionRemoveAll(MessageReactionRemoveAllEvent event) {
		super.onMessageReactionRemoveAll(event);
	}
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		super.onMessageReceived(event);
	}
	@Override
	public void onMessageUpdate(MessageUpdateEvent event) {
		super.onMessageUpdate(event);
	}
	@Override
	public void onPrivateChannelCreate(PrivateChannelCreateEvent event) {
		super.onPrivateChannelCreate(event);
	}
	
	@Override
	public void onPrivateChannelDelete(PrivateChannelDeleteEvent event) {
		super.onPrivateChannelDelete(event);
	}
	@Override
	public void onPrivateMessageDelete(PrivateMessageDeleteEvent event) {
		super.onPrivateMessageDelete(event);
	}
	@Override
	public void onPrivateMessageEmbed(PrivateMessageEmbedEvent event) {
		super.onPrivateMessageEmbed(event);
	}
	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
		super.onPrivateMessageReceived(event);
	}
	@Override
	public void onPrivateMessageUpdate(PrivateMessageUpdateEvent event) {
		super.onPrivateMessageUpdate(event);
	}
	@Override
	public void onReady(ReadyEvent event) {
		super.onReady(event);
	}
	@Override
	public void onReconnect(ReconnectedEvent event) {
		super.onReconnect(event);
	}
	@Override
	public void onResume(ResumedEvent event) {
		super.onResume(event);
	}
	@Override
	public void onRoleCreate(RoleCreateEvent event) {
		super.onRoleCreate(event);
	}
	@Override
	public void onRoleDelete(RoleDeleteEvent event) {
		super.onRoleDelete(event);
	}
	@Override
	public void onRoleUpdateColor(RoleUpdateColorEvent event) {
		super.onRoleUpdateColor(event);
	}
	@Override
	public void onRoleUpdateHoisted(RoleUpdateHoistedEvent event) {
		super.onRoleUpdateHoisted(event);
	}
	@Override
	public void onRoleUpdateMentionable(RoleUpdateMentionableEvent event) {
		super.onRoleUpdateMentionable(event);
	}
	@Override
	public void onRoleUpdateName(RoleUpdateNameEvent event) {
		super.onRoleUpdateName(event);
	}
	@Override
	public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event) {
		super.onRoleUpdatePermissions(event);
	}
	@Override
	public void onRoleUpdatePosition(RoleUpdatePositionEvent event) {
		super.onRoleUpdatePosition(event);
	}
	@Override
	public void onSelfUpdateAvatar(SelfUpdateAvatarEvent event) {
		super.onSelfUpdateAvatar(event);
	}
	@Override
	public void onSelfUpdateEmail(SelfUpdateEmailEvent event) {
		super.onSelfUpdateEmail(event);
	}
	@Override
	public void onSelfUpdateMFA(SelfUpdateMFAEvent event) {
		super.onSelfUpdateMFA(event);
	}
	@Override
	public void onSelfUpdateName(SelfUpdateNameEvent event) {
		super.onSelfUpdateName(event);
	}
	@Override
	public void onSelfUpdateVerified(SelfUpdateVerifiedEvent event) {
		super.onSelfUpdateVerified(event);
	}
	@Override
	public void onShutdown(ShutdownEvent event) {
		super.onShutdown(event);
	}
	@Override
	public void onStatusChange(StatusChangeEvent event) {
		super.onStatusChange(event);
	}
	@Override
	public void onTextChannelCreate(TextChannelCreateEvent event) {
		super.onTextChannelCreate(event);
	}
	@Override
	public void onTextChannelDelete(TextChannelDeleteEvent event) {
		super.onTextChannelDelete(event);
	}
	@Override
	public void onTextChannelUpdateName(TextChannelUpdateNameEvent event) {
		super.onTextChannelUpdateName(event);
	}
	@Override
	public void onTextChannelUpdatePermissions(TextChannelUpdatePermissionsEvent event) {
		super.onTextChannelUpdatePermissions(event);
	}
	@Override
	public void onTextChannelUpdatePosition(TextChannelUpdatePositionEvent event) {
		super.onTextChannelUpdatePosition(event);
	}
	@Override
	public void onTextChannelUpdateTopic(TextChannelUpdateTopicEvent event) {
		super.onTextChannelUpdateTopic(event);
	}
	@Override
	public void onUnavailableGuildJoined(UnavailableGuildJoinedEvent event) {
		super.onUnavailableGuildJoined(event);
	}
	@Override
	public void onUserAvatarUpdate(UserAvatarUpdateEvent event) {
		super.onUserAvatarUpdate(event);
	}
	@Override
	public void onUserBlocked(UserBlockedEvent event) {
		super.onUserBlocked(event);
	}
	 @Override
	public void onUserGameUpdate(UserGameUpdateEvent event) {
		super.onUserGameUpdate(event);
	}
	 @Override
	public void onUserNameUpdate(UserNameUpdateEvent event) {
		super.onUserNameUpdate(event);
	}
	 public void onUserOnlineStatusUpdate(net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent event) {};
	 @Override
	public void onUserTyping(UserTypingEvent event) {
		super.onUserTyping(event);
	}
	 @Override
	public void onUserUnblocked(UserUnblockedEvent event) {
		super.onUserUnblocked(event);
	}
	 @Override
	public void onVoiceChannelCreate(VoiceChannelCreateEvent event) {
		super.onVoiceChannelCreate(event);
	}
	 @Override
	public void onVoiceChannelDelete(VoiceChannelDeleteEvent event) {
		super.onVoiceChannelDelete(event);
	}
	 @Override
	public void onVoiceChannelUpdateBitrate(VoiceChannelUpdateBitrateEvent event) {
		super.onVoiceChannelUpdateBitrate(event);
	}
	 @Override
	public void onVoiceChannelUpdateName(VoiceChannelUpdateNameEvent event) {
		super.onVoiceChannelUpdateName(event);
	}
	 @Override
	public void onVoiceChannelUpdatePermissions(VoiceChannelUpdatePermissionsEvent event) {
		super.onVoiceChannelUpdatePermissions(event);
	}
	 @Override
	public void onVoiceChannelUpdatePosition(VoiceChannelUpdatePositionEvent event) {
		super.onVoiceChannelUpdatePosition(event);
	}
	 @Override
	public void onVoiceChannelUpdateUserLimit(VoiceChannelUpdateUserLimitEvent event) {
		super.onVoiceChannelUpdateUserLimit(event);
	}
}
