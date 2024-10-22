# Music Streaming Application (OOP-Based)

This project is a Spotify-like music streaming platform built using object-oriented programming (OOP) principles. It allows users to interact with a variety of content, including albums, podcasts, and artist events, while managing different user roles like artists and hosts.

## Features

- **User Roles**: 
  - Artists can add albums, events, and merchandise.
  - Hosts can manage podcasts and announcements.
  - Regular users have access to personalized pages (e.g., Liked Content).
  
- **Content Management**: 
  - Create, display, and remove albums, events, podcasts, and merchandise.
  - Seamless handling of user actions based on role-specific permissions.

- **Dynamic Operations**:
  - Switch between different user statuses (online/offline).
  - View all online users and interact with content in real time.
  
- **Search & Interaction**:
  - Search and select artists or hosts with ease.
  - Browse top 5 albums and artists based on popularity metrics.

## Object-Oriented Principles

- **Inheritance**: `Artist` and `Host` extend the base `User` class, inheriting common properties while adding role-specific functionality.
- **Interfaces**: The `ArtistOperations` and `HostOperations` interfaces define clear methods for extending user roles.
- **Design Patterns**: The **Visitor** pattern is implemented to safely check if users, albums, or podcasts are in use before deletion.
- **Lambda Expressions**: Used for efficient sorting and filtering of content, enhancing system performance.

## Technical Overview

- **Backend**: Manages database operations for storing and retrieving albums, podcasts, and user interactions.
- **Content Interaction**: The system supports commands like `addAlbum`, `addPodcast`, and `deleteUser` while ensuring data consistency.
- **Usage Tracking**: Ensures that users or content in use cannot be removed, safeguarding user experience and data integrity.
